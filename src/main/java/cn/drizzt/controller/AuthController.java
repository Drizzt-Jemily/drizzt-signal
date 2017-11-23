package cn.drizzt.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView one(@RequestParam(value = "calling", required = true) String calling,
			@RequestParam(value = "pwd", required = true) String pwd) throws Exception {
		ModelAndView result = new ModelAndView("waiting");
		String id = "";
		if (pwd.equals("drizzt1117")) {
			Pattern pattern = Pattern.compile("[1][34578]\\d{9}");
			Matcher matcher = pattern.matcher("calling");
			if(matcher.matches()){
				SignalAuth signalAuth = new SignalAuth();
				id = Numbers.uuid();
				signalAuth.setId(id);
				calling = signalMobileService.convertCalling(calling, Const.AREA_CODE);
				signalAuth.setCalling(calling);
				signalAuth.setStartTime(System.currentTimeMillis());
				signalAuth.setCallResult(Const.CALL_RESULT_0);
				signalAuthService.add(signalAuth);
			}else{
				id = "-98";
			}
		} else {
			id = "-99";
		}
		result.addObject("id", id);
		return result;
	}

	@RequestMapping(value = "/oneResult/{id}", method = RequestMethod.GET)
	public ModelAndView oneResult(@PathVariable("id") String id) throws Exception {
		ModelAndView result = new ModelAndView("oneResult");
		SignalAuth signalAuth = signalAuthService.getById(id);
		result.addObject("calling", signalAuth.getCalling());
		int callResult = signalAuth.getCallResult();
		String cr = "";
		if (callResult == Const.CALL_RESULT_0) {
			cr = "未呼叫";
		} else if (callResult == Const.CALL_RESULT_99) {
			cr = "正在呼叫";
		} else if (callResult == Const.CALL_RESULT_98) {
			cr = "呼叫超时";
		} else if (callResult == Const.CALL_RESULT_97) {
			cr = "呼叫异常";
		} else if (callResult == Const.CALL_RESULT_1) {
			cr = "正常号码";
		} else if (callResult == Const.CALL_RESULT_2) {
			cr = "被接听";
		} else if (callResult == Const.CALL_RESULT_3) {
			cr = "暂时无法接听";
		} else if (callResult == Const.CALL_RESULT_4) {
			cr = "呼叫限制";
		} else if (callResult == Const.CALL_RESULT_5) {
			cr = "关机";
		} else if (callResult == Const.CALL_RESULT_6) {
			cr = "空号";
		} else if (callResult == Const.CALL_RESULT_7) {
			cr = "停机";
		} else if (callResult == Const.CALL_RESULT_8) {
			cr = "来电提醒";
		} else {
			cr = "未知状态";
		}
		result.addObject("cr", cr);
		return result;
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
