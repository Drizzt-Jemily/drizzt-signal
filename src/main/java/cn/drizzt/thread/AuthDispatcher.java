package cn.drizzt.thread;

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
	private PublicResource publicResource;

	@Autowired
	private SignalAuthService signalAuthService;

	public void run() {
		while (true) {
			SignalAuth waitAuth = signalAuthService.getWaitAuth();
			if (waitAuth != null) {
				ChManager freeChManager = publicResource.getFreeChManager();
				if (freeChManager != null) {
					waitAuth.setCallStatus(Const.CALL_STATUS_99);
					signalAuthService.update(waitAuth);
					freeChManager.setId(waitAuth.getId());
					freeChManager.setCalling(waitAuth.getCalling());
					freeChManager.setCalled("56617550");
					ShUtil.INSTANCE.SsmSetTxCallerId(freeChManager.getCh(), "56617550");
					ShUtil.INSTANCE.SsmAutoDial(freeChManager.getCh(), waitAuth.getCalling());
				}
			}
			try {
				Thread.sleep(Const.DISPATCHER_SLEEPTIME);
			} catch (InterruptedException e) {
				LOGGER.error(ExceptionConstans.getTrace(e));
			}
		}
	}

}
