package cn.drizzt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.drizzt.entity.SignalMobile;
import cn.drizzt.service.SendMessageService;
import cn.drizzt.util.Const;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private SendMessageService sendMessageService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Object auth(HttpServletRequest request) throws Exception {
		for (int i = 0; i < 100; i++) {
			sendMessageService.sendMessage(Const.AUTH_TOPIC, "测试" + i);
			System.out.println("发送消息：" + i);
		}
		SignalMobile signalMobile = new SignalMobile();
		signalMobile.setAreaCode("123");
		signalMobile.setMobileArea("中文");
		return signalMobile;
	}
}
