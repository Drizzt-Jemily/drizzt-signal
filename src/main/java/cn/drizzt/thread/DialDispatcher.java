package cn.drizzt.thread;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.drizzt.entity.SignalAuth;
//import cn.drizzt.model.ChManager;
import cn.drizzt.service.SignalAuthService;
import cn.drizzt.service.SignalUserService;
import cn.drizzt.util.Const;
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
			// Map<Integer, ChManager> chManagerPool = authResource.getChManagerPool();
			// Set<Integer> keySet = chManagerPool.keySet();
			// for (Integer key : keySet) {
			// ChManager chManager = chManagerPool.get(key);
			// LOGGER.debug("判断通道：" + key + "，使用状态：" + chManager.isUseStatus() + "，呼叫状态：" +
			// chManager.isCallStatus());
			// if (chManager.isUseStatus() && !chManager.isCallStatus()) {
			// chManager.setCallStatus(true);
			// Worker worker = new Worker(chManager);
			// dialExecutor.execute(worker);
			// }
			// }

			SignalAuth signalAuth = authResource.getAuth();
			if (null != signalAuth) {
				Worker worker = new Worker(signalAuth);
				dialExecutor.execute(worker);
			}

			try {
				Thread.sleep(Const.DISPATCHER_SLEEPTIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private final class Worker implements Runnable {

		private SignalAuth signalAuth;

		private Worker(SignalAuth signalAuth) {
			this.signalAuth = signalAuth;
		}

		public void run() {
			try {
				handAuthQueue();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * 监听特定线路的呼叫状态
		 * 
		 * @throws InterruptedException
		 */
		private void handAuthQueue() {
			try {
				int ch = signalAuth.getCh();
				signalAuth.setAutoDial(0);
				signalAuth.setToneAnalyze(0);
				signalAuth.setRecordStatus(0);
				signalAuth.setStartTime(System.currentTimeMillis());
				int i = 0;
				boolean b = true; // 循环控制器

				// 控制sip和中继的超时时间
				int timeout = Const.DIAL_TIMEOUT;
				// if (ch < Const.SIP_NUMBER) {
				// timeout = Const.SIP_DIAL_TIMEOUT;
				// }

				while (b && i < timeout) {
					// 每隔8毫秒监测一次通道状态
					i += 8;
					Thread.sleep(8);

					// 初始化
					int autoDial = signalAuth.getAutoDial();
					int toneAnalyze = signalAuth.getToneAnalyze();
					int recordStatus = signalAuth.getRecordStatus();

					// 监听通道数据
					int ssmChkAutoDial = ShUtil.INSTANCE.SsmChkAutoDial(ch);
					int ssmDetectBargeIn = ShUtil.INSTANCE.SsmDetectBargeIn(ch);
					int ssmGetToneAnalyzeResult = ShUtil.INSTANCE.SsmGetToneAnalyzeResult(ch);
					int ssmChkRecToFile = ShUtil.INSTANCE.SsmChkRecToFile(ch);

					// 录音状态判断，如果录音完毕则挂断
					if (recordStatus == 1 && ssmChkRecToFile == 1) {
						signalAuth.setRecordStatus(2);
					}
					if (recordStatus == 2 && ssmChkRecToFile == 0) {
						// 给板卡保存录音的时间
						Thread.sleep(500);
						signalAuth.setRecordStatus(3);
						// ShUtil.INSTANCE.SsmHangup(ch);
						// chManager.setHangup(true);
						b = false;
					}

					// toneAnalyze判断逻辑
					if (ssmGetToneAnalyzeResult == 3) {
						if (toneAnalyze == 0) {
							signalAuth.setToneAnalyze(ssmGetToneAnalyzeResult);
						}
						// ShUtil.INSTANCE.SsmHangup(ch);
						b = false;
						signalAuth.setCallResult(Const.CALL_RESULT_1);
					} else if (ssmGetToneAnalyzeResult == 6) {
						if (toneAnalyze == 0) {
							signalAuth.setToneAnalyze(ssmGetToneAnalyzeResult);
						}
					} else if (ssmGetToneAnalyzeResult == 2) {
						if (toneAnalyze == 0) {
							signalAuth.setToneAnalyze(ssmGetToneAnalyzeResult);
						}
						b = false;
						// signalAuth.setCallResult(Const.CALL_RESULT_4);
						signalAuth.setCallResult(Const.CALL_RESULT_3);
					}

					// autoDial判断逻辑
					if (ssmChkAutoDial == 2 || ssmChkAutoDial == 13) {
						if (autoDial == 0) {
							signalAuth.setAutoDial(ssmChkAutoDial);
						}
						if (recordStatus == 0 && ssmDetectBargeIn == 1) {
							signalAuth.setStartRecordDur(System.currentTimeMillis() - signalAuth.getStartTime());
							ShUtil.INSTANCE.SsmRecToFile(ch,
									Const.CTI_VOICE_PATH + File.separator + signalAuth.getId() + ".wav", -2, 0, 65535,
									Const.RECORD_TIME, 1);
							signalAuth.setRecordStatus(1);
							// 开始录音时增加录音时长
							i = i - Const.RECORD_TIME;
						}
					} else if (ssmChkAutoDial == 7) {
						if (autoDial != 7) {
							signalAuth.setAutoDial(ssmChkAutoDial);
						}
						// ShUtil.INSTANCE.SsmHangup(ch);
						b = false;
						signalAuth.setCallResult(Const.CALL_RESULT_2);
					} else if (ssmChkAutoDial == 11) {
						if (autoDial != 11) {
							signalAuth.setAutoDial(ssmChkAutoDial);
						}
						// ShUtil.INSTANCE.SsmHangup(ch);
						b = false;
						signalAuth.setCallResult(Const.CALL_RESULT_97);
					}

				}

				// 如果在呼叫过程中未挂机，执行挂机操作
				// if (!chManager.isHangup()) {
				// ShUtil.INSTANCE.SsmHangup(ch);
				// }

				if (b) { // 呼叫超时，暂时按正常处理
					// chManager.setCallResult(Const.CALL_RESULT_98);
					// signalAuth.setCallResult(Const.CALL_RESULT_97);
					signalAuth.setCallResult(Const.CALL_RESULT_1);
				}

				// 如果结果未变化，进行语音识别
				if (signalAuth.getCallResult() == 99) {
					if (signalAuth.getRecordStatus() == 3) {
						Long start = System.currentTimeMillis();
						String translation = VoiceUtil.getTranslation(signalAuth.getId() + ".wav");

						// 如果token过期则重新获取
						if (translation.equals("error3302")) {
							VoiceUtil.getToken();
							translation = VoiceUtil.getTranslation(signalAuth.getId() + ".wav");
						}

						signalAuth.setTranslation(translation);
						Long end = System.currentTimeMillis();
						signalAuth.setVoiceDuration(end - start);

						Map<String, Integer> transTable = authResource.getTransTable();
						for (Entry<String, Integer> entry : transTable.entrySet()) {
							if (translation.contains(entry.getKey())) {
								signalAuth.setCallResult(entry.getValue());
								break;
							}
						}
						if (signalAuth.getCallResult() == 99) {
							signalAuth.setCallResult(Const.CALL_RESULT_1);
						}
					} else {
						signalAuth.setCallResult(Const.CALL_RESULT_97);
					}
				}

				// 设置持续时间
				signalAuth.setDuration(System.currentTimeMillis() - signalAuth.getStartTime());

				// 删除录音文件
				File pcmFile = new File(Const.CTI_VOICE_PATH + File.separator + signalAuth.getId() + ".wav");
				if (pcmFile.exists() && !Const.DEBUG) {
					boolean d = pcmFile.delete();
					if (!d) {
						LOGGER.error("文件删除失败：" + signalAuth.getId() + ".wav");
					}
				}

				// 去掉外地号码加拨的0
				// String calling = signalAuth.getCalling();
				// if (calling.startsWith("0")) {
				// signalAuth.setCalling(calling.substring(1, calling.length()));
				// }

				signalAuthService.update(signalAuth);

				// 如果呼叫失败，将钱返还
				if (signalAuth.getCallResult() == Const.CALL_RESULT_97) {
					String userId = signalAuth.getUserId();
					if (null != userId && !"".equals(userId)) {
						// SignalUser user = signalUserService.getById(userId);
						signalUserService.increaseNumber(userId);
						// String url = user.getUrl();
						// if (null != url && !"".equals(url)) {
						// int callResult = chManager.getCallResult();
						// HttpClientUtil.sendHttpPost(url, "calling=" + chManager.getCalling() +
						// "&code=" + callResult
						// + "&msg=" + CallResultCH.getCH(callResult));
						// }
					}
				}

			} catch (Exception e) {
				LOGGER.error("ID号码为：" + signalAuth.getId() + ",本次呼叫出现异常！！！");
				e.printStackTrace();

				// 关闭线路
				// if (!chManager.isHangup()) {
				// ShUtil.INSTANCE.SsmHangup(chManager.getCh());
				// }

				// 属性拷贝并保存数据库
				// SignalAuth signalAuth = new SignalAuth();
				// try {
				//
				// // 去掉外地号码加拨的0
				// String calling = signalAuth.getCalling();
				// if (calling.startsWith("0")) {
				// signalAuth.setCalling(calling.substring(1, calling.length()));
				// }
				//
				// } catch (Exception e1) {
				// e1.printStackTrace();
				// }

				signalAuth.setCallResult(Const.CALL_RESULT_97);
				signalAuthService.update(signalAuth);

			} finally {

				// 关闭线路
				ShUtil.INSTANCE.SsmHangup(signalAuth.getCh());
				// signalAuth.setHangup(true);

				// int i = ShUtil.INSTANCE.SsmGetChState(chManager.getCh());
				//
				// while (i != 0) {
				// try {
				// Thread.sleep(100);
				// } catch (InterruptedException e) {
				// e.printStackTrace();
				// }
				// i = ShUtil.INSTANCE.SsmGetChState(chManager.getCh());
				// }
				//
				// // 增加500毫秒释放通道时间
				// try {
				// Thread.sleep(500);
				// } catch (InterruptedException e) {
				// LOGGER.error(ExceptionConstans.getTrace(e));
				// }
				// // 重置线路
				// authResource.resetChManager(chManager.getCh());
			}
		}
	}
}