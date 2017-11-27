package cn.drizzt.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.drizzt.entity.SignalAuth;
import cn.drizzt.entity.SignalBatch;
import cn.drizzt.model.BatchResponse;
import cn.drizzt.service.SignalAuthService;
import cn.drizzt.service.SignalBatchService;
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

	@Autowired
	private SignalBatchService signalBatchService;

	@RequestMapping(value = "/one", method = RequestMethod.POST)
	public ModelAndView one(@RequestParam(value = "calling", required = true) String calling,
			@RequestParam(value = "pwd", required = true) String pwd) throws Exception {
		ModelAndView result = new ModelAndView("waiting");
		String id = "";
		if (pwd.equals("drizzt1117")) {
			Pattern pattern = Pattern.compile("[1][34578]\\d{9}");
			Matcher callingPattern = pattern.matcher(calling);
			if (callingPattern.matches()) {
				SignalAuth signalAuth = new SignalAuth();
				id = Numbers.uuid();
				signalAuth.setId(id);
				calling = signalMobileService.convertCalling(calling, Const.AREA_CODE);
				signalAuth.setCalling(calling);
				signalAuth.setStartTime(System.currentTimeMillis());
				signalAuth.setCallResult(Const.CALL_RESULT_0);
				signalAuthService.add(signalAuth);
			} else {
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

	@RequestMapping(value = "/batch", method = RequestMethod.POST)
	public ModelAndView batch(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "pwd", required = true) String pwd) throws Exception {
		ModelAndView result = new ModelAndView("batchWaiting");
		String batchId = "";
		List<Integer> is = new ArrayList<Integer>();
		if (pwd.equals("drizzt1117")) {
			if (file.getContentType().equals("text/plain")) {
				Pattern pattern = Pattern.compile("[1][34578]\\d{9}");
				BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
				String calling = null;
				String id = "";
				int i = 1;
				batchId = Numbers.uuid();
				SignalBatch signalBatch = new SignalBatch();
				signalBatch.setId(batchId);
				signalBatch.setReceiveTime(new Date());
				signalBatchService.add(signalBatch);
				SignalAuth signalAuth = new SignalAuth();
				while ((calling = br.readLine()) != null) {
					calling = calling.trim();
					i++;
					Matcher callingPattern = pattern.matcher(calling);
					if (!callingPattern.matches()) {
						is.add(i);
					} else {
						id = Numbers.uuid();
						signalAuth.setId(id);
						calling = signalMobileService.convertCalling(calling, Const.AREA_CODE);
						signalAuth.setCalling(calling);
						signalAuth.setStartTime(System.currentTimeMillis());
						signalAuth.setCallResult(Const.CALL_RESULT_0);
						signalAuth.setBatchId(batchId);
						signalAuthService.add(signalAuth);
					}
				}
				br.close();
			} else {
				batchId = "-98";
			}
		} else {
			batchId = "-99";
		}
		result.addObject("is", is);
		result.addObject("batchId", batchId);
		return result;
	}

	@RequestMapping(value = "/batchResult/{batchId}", method = RequestMethod.GET)
	public ModelAndView batchResult(@PathVariable("batchId") String batchId) throws Exception {
		List<BatchResponse> batchResponses = new ArrayList<BatchResponse>();
		ModelAndView result = new ModelAndView("batchResult");
		List<SignalAuth> signalAuths = signalAuthService.getByBatchId(batchId);
		for (SignalAuth signalAuth : signalAuths) {
			BatchResponse batchResponse = new BatchResponse();
			batchResponse.setCalling(signalAuth.getCalling());
			if (signalAuth.getCallResult() == Const.CALL_RESULT_0) {
				batchResponse.setCallResult("未呼叫");
			} else if (signalAuth.getCallResult() == Const.CALL_RESULT_99) {
				batchResponse.setCallResult("正在呼叫");
			} else if (signalAuth.getCallResult() == Const.CALL_RESULT_98) {
				batchResponse.setCallResult("呼叫超时");
			} else if (signalAuth.getCallResult() == Const.CALL_RESULT_97) {
				batchResponse.setCallResult("呼叫异常");
			} else if (signalAuth.getCallResult() == Const.CALL_RESULT_1) {
				batchResponse.setCallResult("正常号码");
			} else if (signalAuth.getCallResult() == Const.CALL_RESULT_2) {
				batchResponse.setCallResult("被接听");
			} else if (signalAuth.getCallResult() == Const.CALL_RESULT_3) {
				batchResponse.setCallResult("暂时无法接听");
			} else if (signalAuth.getCallResult() == Const.CALL_RESULT_4) {
				batchResponse.setCallResult("呼叫限制");
			} else if (signalAuth.getCallResult() == Const.CALL_RESULT_5) {
				batchResponse.setCallResult("关机");
			} else if (signalAuth.getCallResult() == Const.CALL_RESULT_6) {
				batchResponse.setCallResult("空号");
			} else if (signalAuth.getCallResult() == Const.CALL_RESULT_7) {
				batchResponse.setCallResult("停机");
			} else if (signalAuth.getCallResult() == Const.CALL_RESULT_8) {
				batchResponse.setCallResult("来电提醒");
			} else {
				batchResponse.setCallResult("未知状态");
			}
			batchResponses.add(batchResponse);
		}

		result.addObject("batchResponses", batchResponses);
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
