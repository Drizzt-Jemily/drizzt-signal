package cn.drizzt.thread;

import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.drizzt.entity.SignalAuth;
import cn.drizzt.model.ChManager;
import cn.drizzt.service.SignalAuthService;
import cn.drizzt.util.Const;
import cn.drizzt.util.ExceptionConstans;
import cn.drizzt.util.ShUtil;

@Component
public class AuthDispatcher implements Runnable {

	private static Logger LOGGER = Logger.getLogger(AuthDispatcher.class);

	@Autowired
	private AuthResource authResource;

	@Autowired
	private SignalAuthService signalAuthService;

	public void run() {
		while (true) {
			SignalAuth waitAuth = signalAuthService.getWaitAuth();
			if (waitAuth != null) {
				ChManager freeChManager = authResource.getFreeChManager();
				if (freeChManager != null) {
					int ch = freeChManager.getCh();
					waitAuth.setCallResult(Const.CALL_RESULT_99);
					signalAuthService.update(waitAuth);
					freeChManager.setId(waitAuth.getId());
					freeChManager.setCalling(waitAuth.getCalling());
					freeChManager.setStartTime(System.currentTimeMillis());
					freeChManager.setUseStatus(true);
					freeChManager.setUserId(waitAuth.getUserId());
					
					// 如果不是sip线路，则设置主叫号
					if (ch > Const.SIP_NUMBER) {
						String called = getCalled();
						freeChManager.setCalled(called);
						ShUtil.INSTANCE.SsmSetTxCallerId(ch, called);
					}
					ShUtil.INSTANCE.SsmAutoDial(ch, freeChManager.getCalling());
				}
			}
			try {
				Thread.sleep(Const.DISPATCHER_SLEEPTIME);
			} catch (InterruptedException e) {
				LOGGER.error(ExceptionConstans.getTrace(e));
			}
		}
	}

	private String getCalled() {
		int i = Const.CODE_POOL.length;
		Random random = new Random();
		return Const.CODE_POOL[random.nextInt(i)];
	}

}
