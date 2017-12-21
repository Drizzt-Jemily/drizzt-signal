package cn.drizzt.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.drizzt.entity.SignalAuth;
import cn.drizzt.entity.SignalBatch;
import cn.drizzt.entity.SignalUser;
import cn.drizzt.model.ApiResponse;
import cn.drizzt.model.ApiResult;
import cn.drizzt.model.BatchResponse;
import cn.drizzt.service.SignalAuthService;
import cn.drizzt.service.SignalBatchService;
import cn.drizzt.service.SignalMobileService;
import cn.drizzt.service.SignalUserService;
import cn.drizzt.thread.AuthResource;
import cn.drizzt.util.CallResultCH;
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

	@Autowired
	private SignalUserService signalUserService;

	@Autowired
	private AuthResource authResource;

	@RequestMapping(value = "/one", method = RequestMethod.POST)
	public ModelAndView one(@RequestParam(value = "calling", required = true) String calling,
			@RequestParam(value = "pwd", required = true) String pwd) throws Exception {
		ModelAndView result = new ModelAndView("waiting");
		String id = "";
		if (pwd.equals("drizzt1205")) {
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

		// 暂时解决正在呼叫问题
		if (callResult == 99 && System.currentTimeMillis() - signalAuth.getStartTime() > Const.CHMANAGER_TIMEOUT) {
			callResult = 98;
		}

		String cr = CallResultCH.getCH(callResult);
		result.addObject("cr", cr);
		return result;
	}

	@RequestMapping(value = "/batch", method = RequestMethod.POST)
	public ModelAndView batch(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "pwd", required = true) String pwd) throws Exception {
		ModelAndView result = new ModelAndView("batchWaiting");
		String batchId = "";
		List<Integer> is = new ArrayList<Integer>();
		if (pwd.equals("drizzt1205")) {
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
			int callResult = signalAuth.getCallResult();

			// 暂时解决正在呼叫问题
			if (callResult == 99 && System.currentTimeMillis() - signalAuth.getStartTime() > Const.CHMANAGER_TIMEOUT) {
				callResult = 98;
			}

			batchResponse.setCallResult(CallResultCH.getCH(callResult));
			batchResponses.add(batchResponse);
		}
		result.addObject("batchResponses", batchResponses);
		return result;
	}

	@RequestMapping(value = "/api/{id}/{calling}", method = RequestMethod.POST)
	@ResponseBody
	public Object api(HttpServletRequest request, @PathVariable("id") String id,
			@PathVariable("calling") String calling) throws Exception {
		ApiResponse apiResponse = new ApiResponse();
		SignalUser signalUser = signalUserService.getById(id);
		if (null == signalUser) {
			apiResponse.setCode(-99);
			apiResponse.setMsg("未授权");
		} else {
			if (signalUser.getNumber() > 0) {
				Pattern pattern = Pattern.compile("[1][34578]\\d{9}");
				Matcher callingPattern = pattern.matcher(calling);
				if (callingPattern.matches()) {
					SignalAuth lastCalling = signalAuthService.getByLastCalling(calling);
					if (null != lastCalling && lastCalling.getCallResult() > 0 && lastCalling.getCallResult() < 50) {
						SignalAuth signalAuth = new SignalAuth();
						String authId = Numbers.uuid();
						signalAuth.setId(authId);
						signalAuth.setCalling(calling);
						signalAuth.setStartTime(System.currentTimeMillis());
						signalAuth.setCallResult(lastCalling.getCallResult());
						signalAuth.setUserId(id);
						signalUserService.reduceNumber(id);
						signalAuthService.add(signalAuth);
						apiResponse.setCode(0);
						apiResponse.setMsg(authId);
					} else {
						if (authResource.getFreeNum() > 0) {
							SignalAuth signalAuth = new SignalAuth();
							String authId = Numbers.uuid();
							signalAuth.setId(authId);
							calling = signalMobileService.convertCalling(calling, Const.AREA_CODE);
							signalAuth.setCalling(calling);
							signalAuth.setStartTime(System.currentTimeMillis());
							signalAuth.setCallResult(Const.CALL_RESULT_0);
							signalAuth.setUserId(id);
							signalUserService.reduceNumber(id);
							signalAuthService.add(signalAuth);
							apiResponse.setCode(0);
							apiResponse.setMsg(authId);
						} else {
							apiResponse.setCode(-96);
							apiResponse.setMsg("线路忙，请稍后");
						}
					}
				} else {
					apiResponse.setCode(-98);
					apiResponse.setMsg("号码格式不正确");
				}
			} else {
				apiResponse.setCode(-97);
				apiResponse.setMsg("余额不足");
			}
		}
		return apiResponse;
	}

	@RequestMapping(value = "/apiResult/{id}/{authId}", method = RequestMethod.POST)
	@ResponseBody
	public Object apiResult(HttpServletRequest request, @PathVariable("id") String id,
			@PathVariable("authId") String authId) throws Exception {
		ApiResult apiResult = new ApiResult();
		SignalUser signalUser = signalUserService.getById(id);
		if (null == signalUser) {
			apiResult.setCode(-99);
			apiResult.setMsg("未授权");
		} else {
			SignalAuth signalAuth = signalAuthService.getById(authId);
			if (null != signalAuth) {
				int callResult = signalAuth.getCallResult();

				// 暂时解决正在呼叫问题
				if (callResult == 99) {
					if (System.currentTimeMillis() - signalAuth.getStartTime() > Const.CHMANAGER_TIMEOUT) {
						callResult = Const.CALL_RESULT_98;
					} else {
						callResult = Const.CALL_RESULT_0;
					}
				}

				String cr = CallResultCH.getCH(callResult);
				apiResult.setCode(callResult);
				String calling = signalAuth.getCalling();
				if (calling.startsWith("0")) {
					calling = calling.substring(1, calling.length());
				}
				apiResult.setCalling(calling);
				apiResult.setMsg(cr);
			} else {
				apiResult.setCode(-95);
				apiResult.setMsg("流水号不存在");
			}
		}
		return apiResult;
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
