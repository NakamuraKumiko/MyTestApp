package com.kumiko.springboot.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@Controller
public class WebController3 {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/{num}")
	public ModelAndView index(@PathVariable int num,
			ModelAndView mav) {
		int res = 0;
		for(int i = 1;i <= num;i++)
			res += i;
		mav.addObject("msg", "goukei: " + res);
		mav.addObject("name", "kumiko");
		mav.setViewName("index");
		return mav;
	}
}
