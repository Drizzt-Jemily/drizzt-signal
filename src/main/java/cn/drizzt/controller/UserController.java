package cn.drizzt.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.drizzt.entity.SignalUser;
import cn.drizzt.service.SignalUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private SignalUserService signalUserService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result = new ModelAndView("user/list");
		List<SignalUser> all = signalUserService.getAll();
		result.addObject("all", all);
		return result;
	}

	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public ModelAndView toAdd() {
		ModelAndView result = new ModelAndView("user/add");
		return result;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@RequestParam(value = "pwd", required = true) String pwd, SignalUser signalUser) {
		if (pwd.equals("zxcasd2018")) {
			signalUser.setRegisterTime(new Date());
			signalUserService.add(signalUser);
			return "redirect:/user";
		} else {
			return "user/result";
		}
	}

	@RequestMapping(value = "/toEdit/{id}", method = RequestMethod.GET)
	public ModelAndView toEdit(@PathVariable("id") String id) {
		ModelAndView result = new ModelAndView("user/edit");
		SignalUser user = signalUserService.getById(id);
		result.addObject("user", user);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@RequestParam(value = "pwd", required = true) String pwd, SignalUser signalUser) {
		if (pwd.equals("zxcasd2018")) {
			signalUserService.edit(signalUser);
			return "redirect:/user";
		} else {
			return "user/result";
		}
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String test(@RequestParam(value = "a", required = true) String a,@RequestParam(value = "b", required = true) String b) {
		System.out.println(a+"******************************");
		System.out.println(b);
		return "user/result";
	}

}
