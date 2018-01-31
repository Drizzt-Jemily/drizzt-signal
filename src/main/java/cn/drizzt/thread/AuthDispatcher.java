package cn.drizzt.thread;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.drizzt.entity.SignalAuth;
import cn.drizzt.service.SignalAuthService;
import cn.drizzt.util.Const;
import cn.drizzt.util.ShUtil;

@Component
public class AuthDispatcher implements Runnable {

	@Autowired
	private AuthResource authResource;

	@Autowired
	private SignalAuthService signalAuthService;

	public void run() {
		while (true) {
			SignalAuth waitAuth = signalAuthService.getWaitAuth();
			if (waitAuth != null) {
				// ChManager freeChManager = authResource.getFreeChManager();
				// if (freeChManager != null) {
				// int ch = freeChManager.getCh();
				// waitAuth.setCallResult(Const.CALL_RESULT_99);
				// signalAuthService.update(waitAuth);
				// freeChManager.setId(waitAuth.getId());
				// freeChManager.setCalling(waitAuth.getCalling());
				// freeChManager.setStartTime(System.currentTimeMillis());
				// freeChManager.setUseStatus(true);
				// freeChManager.setUserId(waitAuth.getUserId());
				//
				// // // 如果不是sip线路，则设置主叫号
				// // if (ch > Const.SIP_NUMBER) {
				// // String called = getCalled();
				// // freeChManager.setCalled(called);
				// // ShUtil.INSTANCE.SsmSetTxCallerId(ch, called);
				// // }
				// // ShUtil.INSTANCE.SsmAutoDial(ch, freeChManager.getCalling());
				//
				// String called = getCalled();
				// freeChManager.setCalled(called);
				// ShUtil.INSTANCE.SsmSetTxCallerId(ch, called);
				//
				// int i = ShUtil.INSTANCE.SsmSearchIdleCallOutCh(0x0408, 0xffff);
				// System.out.println("逻辑通道:" + freeChManager.getCh() + "==========空闲通道:" + i);
				// System.out.println("错误信息：" + ShUtil.INSTANCE.SsmGetLastErrMsgA());
				// }

				int ch = ShUtil.INSTANCE.SsmSearchIdleCallOutCh(0x0408, 0xffff);
				System.out.println("空闲通道：" + ch);
				if (ch != -1) {
					String called = getCalled();
					waitAuth.setCalled(called);
					waitAuth.setCh(ch);
					waitAuth.setCallResult(Const.CALL_RESULT_99);
					signalAuthService.update(waitAuth);
					ShUtil.INSTANCE.SsmSetTxCallerId(ch, called);
					ShUtil.INSTANCE.SsmAutoDial(ch, waitAuth.getCalling());
					authResource.addAuth(waitAuth);
				}

			}
			try {
				Thread.sleep(Const.DISPATCHER_SLEEPTIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private String getCalled() {
		int i = Const.CODE_POOL.length;
		Random random = new Random();
		return Const.CODE_POOL[random.nextInt(i)];
	}

}
