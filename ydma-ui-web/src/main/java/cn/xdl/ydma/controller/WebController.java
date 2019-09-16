package cn.xdl.ydma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView av = new ModelAndView("index");//templates/index.html
		return av;
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/regist")
	public String regist() {
		return "regist";
	}
	
	@RequestMapping("/course")
	public String course() {
		return "course";
	}

	@RequestMapping("/usercenter")
	public String usercenter() {
		return "user_center";
	}
	
}
