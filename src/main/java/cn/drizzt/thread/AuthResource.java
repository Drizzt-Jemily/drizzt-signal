package cn.drizzt.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.drizzt.entity.SignalAuth;
import cn.drizzt.util.Const;
import cn.drizzt.util.ShUtil;
import cn.drizzt.util.VoiceUtil;

@Component
public class AuthResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthResource.class);

	@Autowired
	private AuthDispatcher authDispatcher;

	@Autowired
	private DialDispatcher dialDispatcher;

//	private Map<Integer, ChManager> chManagerPool = new ConcurrentHashMap<Integer, ChManager>();

	private Map<String, Integer> transTable = new HashMap<String, Integer>();
	
	private ConcurrentLinkedQueue<SignalAuth> authQueue = new ConcurrentLinkedQueue<SignalAuth>();

	@Value("${calling.ip}")  
    private String callingIp;
	
	public void init() throws Exception {
		int i = ShUtil.INSTANCE.SsmStartCti(Const.CTI_INI_PATH + "ShConfig.ini", Const.CTI_INI_PATH + "ShIndex.ini");
		if (i == 0) {
			Thread.sleep(Const.CARD_SLEEPTIME);
			LOGGER.info("板卡启动成功");

			// 初始化sip线路，从0开始
			// for (int j = 0; j < Const.SIP_NUMBER; j++) {
			// int state = ShUtil.INSTANCE.SsmGetChState(j);
			// LOGGER.info(j + "通道状态：" + state);
			// if (state == 0) {
			// chManagerPool.put(j, new ChManager(j));
			// }
			// }

			// 初始化中继线路，从120开始
//			for (int j = 120; j < Const.CARD_NUMBER + 120; j++) {
//				int state = ShUtil.INSTANCE.SsmGetChState(j);
//				LOGGER.info(j + "通道状态：" + state);
//				if (state == 0) {
//					chManagerPool.put(j, new ChManager(j));
//				}
//			}

			transTable.put("空号", Const.CALL_RESULT_6);
			transTable.put("不存在", Const.CALL_RESULT_6);
			transTable.put("不再使用", Const.CALL_RESULT_6);
			transTable.put("不在使用", Const.CALL_RESULT_6);
			transTable.put("语音通话", Const.CALL_RESULT_6);
			transTable.put("已过期", Const.CALL_RESULT_6);
			transTable.put("不在使用中", Const.CALL_RESULT_6);
			transTable.put("欠费", Const.CALL_RESULT_7);
			transTable.put("停机", Const.CALL_RESULT_7);
			transTable.put("暂停", Const.CALL_RESULT_7);
			transTable.put("通话中", Const.CALL_RESULT_3);
			transTable.put("无法接通", Const.CALL_RESULT_3);
			transTable.put("不方便", Const.CALL_RESULT_3);
			transTable.put("用户正忙", Const.CALL_RESULT_3);
			transTable.put("对方正忙", Const.CALL_RESULT_3);
			transTable.put("现在正忙", Const.CALL_RESULT_3);
			transTable.put("暂时无法", Const.CALL_RESULT_3);
			transTable.put("接听电话", Const.CALL_RESULT_3);
			transTable.put("限制", Const.CALL_RESULT_4);
			transTable.put("设置", Const.CALL_RESULT_4);
			transTable.put("未开通", Const.CALL_RESULT_4);
			transTable.put("语音服务", Const.CALL_RESULT_4);
			transTable.put("关机", Const.CALL_RESULT_5);
			transTable.put("已关", Const.CALL_RESULT_5);
			transTable.put("提醒", Const.CALL_RESULT_8);
			transTable.put("提示", Const.CALL_RESULT_8);
			transTable.put("秘书", Const.CALL_RESULT_8);
			transTable.put("呼转", Const.CALL_RESULT_8);
			transTable.put("转至", Const.CALL_RESULT_8);
			transTable.put("短信呼", Const.CALL_RESULT_8);
			transTable.put("滴声后留言", Const.CALL_RESULT_8);
			transTable.put("语音信箱", Const.CALL_RESULT_8);
			transTable.put("通信助理", Const.CALL_RESULT_8);
			transTable.put("呼叫前转", Const.CALL_RESULT_8);
			transTable.put("来电助手", Const.CALL_RESULT_8);
			transTable.put("转移到", Const.CALL_RESULT_8);

			// 英文识别
			transTable.put("乐风原创文艺炮", Const.CALL_RESULT_5);
			transTable.put("鱼肝油的泡", Const.CALL_RESULT_5);
			transTable.put("一张由的泡", Const.CALL_RESULT_5);
			transTable.put("一张由黑袍老", Const.CALL_RESULT_5);
			transTable.put("加油的泡老师", Const.CALL_RESULT_5);
			transTable.put("很张扬的那种", Const.CALL_RESULT_6);
			transTable.put("很长一的那点事", Const.CALL_RESULT_6);
			transTable.put("哪里近", Const.CALL_RESULT_6);
			transTable.put("哪里镇", Const.CALL_RESULT_6);
			transTable.put("哪里进", Const.CALL_RESULT_6);
			transTable.put("剩下来的", Const.CALL_RESULT_6);
			transTable.put("更加累的", Const.CALL_RESULT_6);
			transTable.put("那点吃的", Const.CALL_RESULT_7);
			transTable.put("dlc日文", Const.CALL_RESULT_7);
			transTable.put("早点吃呗", Const.CALL_RESULT_7);
			transTable.put("早点四十呗", Const.CALL_RESULT_7);
			transTable.put("射杀yahoo思维", Const.CALL_RESULT_7);

			VoiceUtil.getToken();

			new Thread(authDispatcher).start();
			new Thread(dialDispatcher).start();

		} else {
			LOGGER.info("板卡启动失败:" + ShUtil.INSTANCE.SsmGetLastErrMsgA());
		}
	}
	

	public String getCallingIp() {
		return callingIp;
	}

	public Map<String, Integer> getTransTable() {
		return transTable;
	}

//	public Map<Integer, ChManager> getChManagerPool() {
//		return chManagerPool;
//	}
//
//	public void resetChManager(int ch) {
//		chManagerPool.put(ch, new ChManager(ch));
//	}

//	public synchronized ChManager getFreeChManager() {
//		ChManager chManager = null;
//		for (Entry<Integer, ChManager> entry : chManagerPool.entrySet()) {
//			if (!entry.getValue().isUseStatus()) {
//				return entry.getValue();
//			} else { // 释放过期通道
//				if (System.currentTimeMillis() - entry.getValue().getStartTime() > Const.CHMANAGER_TIMEOUT) {
//					if (!entry.getValue().isHangup()) {
//						ShUtil.INSTANCE.SsmHangup(entry.getValue().getCh());
//					}
//					resetChManager(entry.getKey());
//					return entry.getValue();
//				}
//			}
//		}
//		return chManager;
//	}
//
//	public int getFreeNum() {
//		int i = 0;
//		for (Entry<Integer, ChManager> entry : chManagerPool.entrySet()) {
//			if (!entry.getValue().isUseStatus()) {
//				i++;
//			}
//		}
//		return i;
//	}
	
	public void addAuth(SignalAuth auth) {
		authQueue.add(auth);
	}

	public SignalAuth getAuth() {
		return authQueue.poll();
	}

	public int getQueueNum() {
		return authQueue.size();
	}

}
