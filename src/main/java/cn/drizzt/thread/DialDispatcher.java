package cn.drizzt.thread;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.drizzt.entity.SignalAuth;
import cn.drizzt.entity.SignalUser;
import cn.drizzt.model.ChManager;
import cn.drizzt.service.SignalAuthService;
import cn.drizzt.service.SignalUserService;
import cn.drizzt.util.CallResultCH;
import cn.drizzt.util.Const;
import cn.drizzt.util.ExceptionConstans;
import cn.drizzt.util.HttpClientUtil;
import cn.drizzt.util.ShUtil;
import cn.drizzt.util.VoiceUtil;

@Component
public class DialDispatcher implements Runnable {

	private static Logger LOGGER = Logger.getLogger(DialDispatcher.class);

	@Resource(name = "dialExecutor")
	private ThreadPoolExecutor dialExecutor;

	@Autowired
	private AuthResource authResource;

	@Autowired
	private SignalAuthService signalAuthService;

	@Autowired
	private SignalUserService signalUserService;

	public void run() {
		while (true) {
			Map<Integer, ChManager> chManagerPool = authResource.getChManagerPool();
			Set<Integer> keySet = chManagerPool.keySet();
			for (Integer key : keySet) {
				ChManager chManager = chManagerPool.get(key);
				LOGGER.debug("判断通道：" + key + "，使用状态：" + chManager.isUseStatus() + "，呼叫状态：" + chManager.isCallStatus());
				if (chManager.isUseStatus() && !chManager.isCallStatus()) {
					chManager.setCallStatus(true);
					Worker worker = new Worker(chManager);
					dialExecutor.execute(worker);
				}
			}
			try {
				Thread.sleep(Const.DISPATCHER_SLEEPTIME);
			} catch (InterruptedException e) {
				LOGGER.error(ExceptionConstans.getTrace(e));
			}
		}
	}

	private final class Worker implements Runnable {

		private ChManager chManager;

		private Worker(ChManager chManager) {
			this.chManager = chManager;
		}

		public void run() {
			try {
				handAuthQueue();
			} catch (Exception e) {
				LOGGER.error(ExceptionConstans.getTrace(e));
			}
		}

		/**
		 * 监听特定线路的呼叫状态
		 * 
		 * @throws InterruptedException
		 */
		@SuppressWarnings("unused")
		private void handAuthQueue() {
			try {
				int ch = chManager.getCh();
				int i = 0;
				boolean b = true; // 循环控制器

				while (b && i < Const.DIAL_TIMEOUT) {
					// 每隔8毫秒监测一次通道状态
					i += 8;
					Thread.sleep(8);

					// 初始化
					int autoDial = chManager.getAutoDial();
					int toneAnalyze = chManager.getToneAnalyze();
					int recordStatus = chManager.getRecordStatus();

					// 监听通道数据
					int ssmChkAutoDial = ShUtil.INSTANCE.SsmChkAutoDial(ch);
					int ssmDetectBargeIn = ShUtil.INSTANCE.SsmDetectBargeIn(ch);
					int ssmGetToneAnalyzeResult = ShUtil.INSTANCE.SsmGetToneAnalyzeResult(ch);
					int ssmChkRecToFile = ShUtil.INSTANCE.SsmChkRecToFile(ch);
					LOGGER.debug("ID：" + chManager.getId() + ",通道号：" + ch + ",autoDial状态：" + ssmChkAutoDial + ",tone状态："
							+ ssmGetToneAnalyzeResult + ",bargeIn状态：" + ssmDetectBargeIn + ",录音状态：" + ssmChkRecToFile);

					// 录音状态判断，如果录音完毕则挂断
					if (recordStatus == 1 && ssmChkRecToFile == 1) {
						chManager.setRecordStatus(2);
					}
					if (recordStatus == 2 && ssmChkRecToFile == 0) {
						chManager.setRecordStatus(3);
						ShUtil.INSTANCE.SsmHangup(ch);
						chManager.setHangup(true);
						b = false;
					}

					// toneAnalyze判断逻辑
					if (ssmGetToneAnalyzeResult == 3) {
						if (toneAnalyze == 0) {
							chManager.setToneAnalyze(ssmGetToneAnalyzeResult);
						}
						ShUtil.INSTANCE.SsmHangup(ch);
						b = false;
						chManager.setCallResult(Const.CALL_RESULT_1);
					} else if (ssmGetToneAnalyzeResult == 6) {
						if (toneAnalyze == 0) {
							chManager.setToneAnalyze(ssmGetToneAnalyzeResult);
						}
					}

					// autoDial判断逻辑
					if (ssmChkAutoDial == 2) {
						if (autoDial == 0) {
							chManager.setAutoDial(ssmChkAutoDial);
						}
						if (recordStatus == 0 && ssmDetectBargeIn == 1) {
							chManager.setStartRecordDur(System.currentTimeMillis() - chManager.getStartTime());
							ShUtil.INSTANCE.SsmRecToFile(ch,
									Const.CTI_VOICE_PATH + File.separator + chManager.getId() + ".wav", -2, 0, 65535,
									Const.RECORD_TIME, 1);
							chManager.setRecordStatus(1);
							// 开始录音时增加录音时长
							i = i - Const.RECORD_TIME;
						}
					} else if (ssmChkAutoDial == 7) {
						if (autoDial != 7) {
							chManager.setAutoDial(ssmChkAutoDial);
						}
						ShUtil.INSTANCE.SsmHangup(ch);
						b = false;
						chManager.setCallResult(Const.CALL_RESULT_2);
					} else if (ssmChkAutoDial == 11) {
						if (autoDial != 11) {
							chManager.setAutoDial(ssmChkAutoDial);
						}
						ShUtil.INSTANCE.SsmHangup(ch);
						b = false;
						chManager.setCallResult(Const.CALL_RESULT_97);
					}

				}

				// 如果在呼叫过程中未挂机，执行挂机操作
				if (!chManager.isHangup()) {
					ShUtil.INSTANCE.SsmHangup(ch);
				}

				if (b) { // 呼叫超时，暂时按正常处理
					// chManager.setCallResult(Const.CALL_RESULT_98);
					chManager.setCallResult(Const.CALL_RESULT_1);
				}

				// 如果结果未变化，进行语音识别
				if (chManager.getCallResult() == 99) {
					if (chManager.getRecordStatus() == 3) {
						Long start = System.currentTimeMillis();
						String translation = VoiceUtil.getTranslation(chManager.getId() + ".wav");

						// 如果token过期则重新获取
						if (translation.equals("error3302")) {
							VoiceUtil.getToken();
							translation = VoiceUtil.getTranslation(chManager.getId() + ".wav");
						}

						chManager.setTranslation(translation);
						Long end = System.currentTimeMillis();
						chManager.setVoiceDuration(end - start);

						Map<String, Integer> transTable = authResource.getTransTable();
						for (Entry<String, Integer> entry : transTable.entrySet()) {
							if (translation.contains(entry.getKey())) {
								chManager.setCallResult(entry.getValue());
								break;
							}
						}
						if (chManager.getCallResult() == 99) {
							chManager.setCallResult(Const.CALL_RESULT_1);
						}
					} else {
						chManager.setCallResult(Const.CALL_RESULT_97);
					}
				}

				// 设置持续时间
				chManager.setDuration(System.currentTimeMillis() - chManager.getStartTime());

				// 删除录音文件
				File pcmFile = new File(Const.CTI_VOICE_PATH + File.separator + chManager.getId() + ".wav");
				if (pcmFile.exists() && !Const.DEBUG) {
					boolean d = pcmFile.delete();
					if (!d) {
						LOGGER.error("文件删除失败：" + chManager.getId() + ".wav");
					}
				}

				// 属性拷贝并保存数据库
				SignalAuth signalAuth = new SignalAuth();

				// 去掉外地号码加拨的0
				String calling = chManager.getCalling();
				if (calling.startsWith("0")) {
					chManager.setCalling(calling.substring(1, calling.length()));
				}

				BeanUtils.copyProperties(signalAuth, chManager);
				signalAuthService.update(signalAuth);

			} catch (Exception e) {
				LOGGER.error("ID号码为：" + chManager.getId() + ",本次呼叫出现异常！！！");
				LOGGER.error(ExceptionConstans.getTrace(e));

				// 关闭线路
				if (!chManager.isHangup()) {
					ShUtil.INSTANCE.SsmHangup(chManager.getCallResult());
				}

				// 属性拷贝并保存数据库
				SignalAuth signalAuth = new SignalAuth();
				try {

					// 去掉外地号码加拨的0
					String calling = chManager.getCalling();
					if (calling.startsWith("0")) {
						chManager.setCalling(calling.substring(1, calling.length()));
					}

					BeanUtils.copyProperties(signalAuth, chManager);
				} catch (Exception e1) {
					LOGGER.error(ExceptionConstans.getTrace(e1));
				}
				signalAuth.setCallResult(Const.CALL_RESULT_97);
				signalAuthService.update(signalAuth);

				// 如果有呼叫结果，扣费，然后回传给用户
				if (chManager.getCallResult() != Const.CALL_RESULT_97) {
					String userId = chManager.getUserId();
					if (null != userId && !"".equals(userId)) {
						SignalUser user = signalUserService.getById(userId);
						signalUserService.reduceNumber(userId);
						String url = user.getUrl();
						if (null != url && !"".equals(url)) {
							int callResult = chManager.getCallResult();
							HttpClientUtil.sendHttpPost(url, "calling=" + chManager.getCalling() + "&code=" + callResult
									+ "&msg=" + CallResultCH.getCH(callResult));
						}
					}
				}

			} finally {

				// 增加300毫秒释放通道时间
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 重置线路
				authResource.resetChManager(chManager.getCh());
			}
		}
	}
}