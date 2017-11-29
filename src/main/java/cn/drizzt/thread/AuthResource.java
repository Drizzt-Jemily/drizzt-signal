package cn.drizzt.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.drizzt.model.ChManager;
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

	private Map<Integer, ChManager> chManagerPool;

	private Map<String, Integer> transTable;

	public void init() throws Exception {
		int i = ShUtil.INSTANCE.SsmStartCti(Const.CTI_INI_PATH + "ShConfig.ini", Const.CTI_INI_PATH + "ShIndex.ini");
		if (i == 0) {
			Thread.sleep(Const.CARD_SLEEPTIME);
			LOGGER.info("板卡启动成功");
			chManagerPool = new ConcurrentHashMap<Integer, ChManager>();
			for (int j = 120; j < Const.CARD_NUMBER+120; j++) {
				int state = ShUtil.INSTANCE.SsmGetChState(j);
				LOGGER.info(j + "通道状态：" + state);
				if (state == 0) {
					chManagerPool.put(j, new ChManager(j));
				}
			}

			transTable = new HashMap<String, Integer>();
			transTable.put("不要", Const.CALL_RESULT_3);
			transTable.put("通话", Const.CALL_RESULT_3);
			transTable.put("忙", Const.CALL_RESULT_3);
			transTable.put("接通", Const.CALL_RESULT_3);
			transTable.put("不方便", Const.CALL_RESULT_3);
			transTable.put("限制", Const.CALL_RESULT_4);
			transTable.put("设置", Const.CALL_RESULT_4);
			transTable.put("关机", Const.CALL_RESULT_5);
			transTable.put("已关", Const.CALL_RESULT_5);
			transTable.put("空", Const.CALL_RESULT_6);
			transTable.put("不存在", Const.CALL_RESULT_6);
			transTable.put("欠费", Const.CALL_RESULT_7);
			transTable.put("停机", Const.CALL_RESULT_7);
			transTable.put("秘书", Const.CALL_RESULT_8);
			transTable.put("提醒", Const.CALL_RESULT_8);
			transTable.put("提示", Const.CALL_RESULT_8);
			transTable.put("呼转", Const.CALL_RESULT_8);

			VoiceUtil.getToken();

			new Thread(authDispatcher).start();
			new Thread(dialDispatcher).start();

		} else {
			LOGGER.info("板卡启动失败:" + ShUtil.INSTANCE.SsmGetLastErrMsgA());
		}
	}

	public Map<String, Integer> getTransTable() {
		return transTable;
	}

	public Map<Integer, ChManager> getChManagerPool() {
		return chManagerPool;
	}

	public void resetChManager(int ch) {
		chManagerPool.put(ch, new ChManager(ch));
	}

	public synchronized ChManager getFreeChManager() {
		ChManager chManager = null;
		for (Entry<Integer, ChManager> entry : chManagerPool.entrySet()) {
			if (!entry.getValue().isUseStatus()) {
				return entry.getValue();
			} else { // 释放过期通道
				if (System.currentTimeMillis() - entry.getValue().getStartTime() > Const.CHMANAGER_TIMEOUT) {
					if (!entry.getValue().isHangup()) {
						ShUtil.INSTANCE.SsmHangup(entry.getValue().getCh());
					}
					resetChManager(entry.getKey());
					return entry.getValue();
				}
			}
		}
		return chManager;
	}

	public int getFreeNum() {
		int i = 0;
		for (Entry<Integer, ChManager> entry : chManagerPool.entrySet()) {
			if (!entry.getValue().isUseStatus()) {
				i++;
			}
		}
		return i;
	}

}
