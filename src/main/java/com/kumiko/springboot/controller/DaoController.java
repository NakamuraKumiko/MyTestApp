package com.kumiko.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kumiko.springboot.dao.MemberDaoImpl;
import com.kumiko.springboot.entity.Member;
import com.kumiko.springboot.entity.UserComment;
import com.kumiko.springboot.entity.UserCommentNum;
import com.kumiko.springboot.repositories.UserCommentNumRepository;
import com.kumiko.springboot.repositories.UserCommentRepository;

@Controller
public class DaoController {

//	@PersistenceContext		//EntityManagerのbeanを取得 @Autowiredとどう違うのか？
//	EntityManager entityManager; //●

	@Autowired
	MemberDaoImpl mdao;

    @Autowired
    UserCommentRepository udao;

    @Autowired
    UserCommentNumRepository undao;

	//初期化
//	@PostConstruct	//コンストラクタより呼び出されるメソッド
//	public void init() {
//		mdao = new MemberDaoImpl(entityManager); //●
//	}

	//ランクページへ遷移
	@RequestMapping(value = "/rank", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {

		mav.setViewName("rankResult");
		mav.addObject("msg", "コメント数ランキング");

		Iterable<Member> list = mdao.getAll(); //●
		mav.addObject("datalist", list);

		Member mid = mdao.findById(3);
		mav.addObject("memberbyid", mid);

		Iterable<Member> list2 = mdao.findByName("花"); //●
		mav.addObject("datalist2", list2);

		return mav;
	}

	//ネイティブSQLテスト
	@RequestMapping(value = "/native", method = RequestMethod.GET)
	public ModelAndView index2(ModelAndView mav) {

		mav.setViewName("nativeResult");
		mav.addObject("msg", "ネイティブSQLテスト");

		List<UserComment> uclist=udao.findUserComment2();
		mav.addObject("uclist", uclist);

		List<UserCommentNum> ucnlist=undao.findUserCommentNum();
		mav.addObject("ucnlist", ucnlist);


		return mav;
	}

}
