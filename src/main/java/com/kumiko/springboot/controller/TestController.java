package com.kumiko.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//@Controller
public class TestController {

	@RequestMapping(value = "/forward", method = RequestMethod.GET)
	public ModelAndView next1() {
		//mav.setViewName("next");

		ModelAndView mav2 = new ModelAndView("forward:/forward1");
		mav2.addObject("msg", "help!forward");
		return mav2;
	}

	@RequestMapping(value = "/forward1")
	public ModelAndView next3(ModelAndView mav) {
		mav.setViewName("next");
//		mav.addObject("msg", "help!forward1");
		return mav;
	}


	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public ModelAndView next2() {
		ModelAndView mav2 = new ModelAndView("redirect:/redirect1");
		mav2.addObject("msg", "helpRedirect");
		return mav2;
	}

	@RequestMapping(value = "/redirect1", method = RequestMethod.GET)
	public ModelAndView next4(ModelAndView mav) {
		mav.setViewName("next");
//		mav.addObject("msg", "help!redirect1");
		return mav;
	}
}
