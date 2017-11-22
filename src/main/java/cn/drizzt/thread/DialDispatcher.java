package cn.drizzt.thread;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.drizzt.model.ChManager;
import cn.drizzt.util.Const;
import cn.drizzt.util.ExceptionConstans;
import cn.drizzt.util.ShUtil;

@Component
public class DialDispatcher implements Runnable {

	private static Logger LOGGER = Logger.getLogger(DialDispatcher.class);

	@Resource(name = "dialExecutor")
	private ThreadPoolExecutor dialExecutor;

	@Autowired
	private PublicResource publicResource;

	public void run() {
		while (true) {
			Map<Integer, ChManager> chManagerPool = publicResource.getChManagerPool();
			Set<Integer> keySet = chManagerPool.keySet();
			for (Integer key : keySet) {
				ChManager chManager = chManagerPool.get(key);
				LOGGER.info("判断通道：" + key + "，使用状态：" + chManager.isUseStatus() + "，呼叫状态：" + chManager.isCallStatus());
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
		 * 处理消息队列
		 * 
		 * @throws InterruptedException
		 */
		private void handAuthQueue() {
			int ch = chManager.getCh();
			int i = 0;
			boolean b = true; // 循环控制器
			while (b && i < Const.DIAL_TIMEOUT) {
				i += 8;
				try {
					Thread.sleep(8);
				} catch (InterruptedException e) {
					LOGGER.error(ExceptionConstans.getTrace(e));
				}
				int ssmChkAutoDial = ShUtil.INSTANCE.SsmChkAutoDial(ch);
				int detectBargeIn = ShUtil.INSTANCE.SsmDetectBargeIn(ch);
				LOGGER.info("ID：" + chManager.getId() + ",通道号：" + ch + ",通道状态为：" + ssmChkAutoDial + ",bargeIn状态"
						+ detectBargeIn);
			}
			ShUtil.INSTANCE.SsmHangup(ch);
			publicResource.resetChManager(ch);
		}
	}
}