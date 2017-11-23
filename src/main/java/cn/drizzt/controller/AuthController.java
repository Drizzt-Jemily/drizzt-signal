package cn.drizzt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.drizzt.entity.SignalAuth;
import cn.drizzt.service.SignalAuthService;
import cn.drizzt.service.SignalMobileService;
import cn.drizzt.util.Const;
import cn.drizzt.util.Numbers;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private SignalAuthService signalAuthService;

	@Autowired
	private SignalMobileService signalMobileService;

	@RequestMapping(value = "/one", method = RequestMethod.POST)
	public Object one(@RequestParam(value = "calling", required = true) String calling,
			@RequestParam(value = "pwd", required = true) String pwd) throws Exception {
		if (pwd.equals("drizzt1117")) {
			SignalAuth signalAuth = new SignalAuth();
			signalAuth.setId(Numbers.uuid());
			calling = signalMobileService.convertCalling(calling, Const.AREA_CODE);
			signalAuth.setCalling(calling);
			signalAuth.setStartTime(System.currentTimeMillis());
			signalAuth.setCallResult(Const.CALL_RESULT_0);
			signalAuthService.add(signalAuth);
		}
		return "/waiting";
	}

	// @Autowired
	// private SendMessageService sendMessageService;
	//
	// @RequestMapping(method = RequestMethod.GET)
	// @ResponseBody
	// public Object auth(HttpServletRequest request) throws Exception {
	// for (int i = 0; i < 100; i++) {
	// sendMessageService.sendMessage(Const.AUTH_TOPIC, "测试" + i);
	// System.out.println("发送消息：" + i);
	// }
	// SignalMobile signalMobile = new SignalMobile();
	// signalMobile.setAreaCode("123");
	// signalMobile.setMobileArea("中文");
	// return signalMobile;
	// }
}
