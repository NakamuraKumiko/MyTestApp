package com.kumiko.springboot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kumiko.springboot.entity.LoginUser;
import com.kumiko.springboot.repositories.LoginUserRepository;

@Controller
public class LoginController {

	@Autowired
	LoginUserRepository lrepository;

	@Autowired
	HttpSession session;

	/*
	 * オブジェクトをHTTPセッションに追加する
	 */
//	@ModelAttribute("member")
//	private Member setMember() {
//		return new Member();
//	}

	//ログインページへ遷移
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("login");
		mav.addObject("msg", "ログインIDとパスワードを入力してください。");
		return mav;
	}

	//検索結果ページへ遷移
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView send(@RequestParam("loginid") String loginid,
			@RequestParam("password") String pass,
			ModelAndView mav) {

		LoginUser loginUser = lrepository.findByLoginIdAndPass(loginid, pass);
		//LoginUser loginUser = user.get();

		if ( loginUser == null ) {
		    mav.addObject("msg", "ログインIDまたはパスワードが違います");
			mav.setViewName("login");
		}else {
//			セッションへ保存
			session.setAttribute("user", loginUser);
			mav.setViewName("top");
		}
		return mav;
	}

	//BootStrapテストページへ遷移
	@RequestMapping(value = "/bsTest", method = RequestMethod.GET)
	public ModelAndView bsindex(ModelAndView mav) {
		mav.setViewName("bsTest");
		return mav;
	}

}
