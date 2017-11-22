package cn.drizzt.thread;

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

@Component
public class PublicResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(PublicResource.class);

	@Autowired
	private AuthDispatcher authDispatcher;

	@Autowired
	private DialDispatcher dialDispatcher;

	private Map<Integer, ChManager> chManagerPool;

	public void init() throws Exception {
		int i = ShUtil.INSTANCE.SsmStartCti(Const.CTI_INI_PATH + "ShConfig.ini", Const.CTI_INI_PATH + "ShIndex.ini");
		if (i == 0) {
			Thread.sleep(Const.CARD_SLEEPTIME);
			LOGGER.info("板卡启动成功");
			chManagerPool = new ConcurrentHashMap<Integer, ChManager>();
			for (int j = 0; j < Const.CARD_NUMBER; j++) {
				int state = ShUtil.INSTANCE.SsmGetChState(j);
				LOGGER.info(j + "通道状态：" + state);
				if (state == 0) {
					chManagerPool.put(j, new ChManager(j));
				}
			}

			new Thread(authDispatcher).start();
			new Thread(dialDispatcher).start();

		} else {
			LOGGER.info("板卡启动失败:" + ShUtil.INSTANCE.SsmGetLastErrMsgA());
		}
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
