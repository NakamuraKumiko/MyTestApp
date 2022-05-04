package com.kumiko.springboot.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.kumiko.springboot.entity.LoginUser;
import com.kumiko.springboot.entity.Member;
import com.kumiko.springboot.entity.MsgData;
import com.kumiko.springboot.repositories.MemberRepository;
import com.kumiko.springboot.repositories.MsgRepository;

@Controller
//@SessionAttributesは1つのController内で扱う複数のリクエスト間で
//データを共有する場合に有効。
//types属性にHTTPセッションに格納するオブジェクトクラスを指定する。
@SessionAttributes(types = Member.class)

public class ShopController {

	@Autowired
	MemberRepository repository;

	@Autowired
	MsgRepository mrepository;

	@Autowired
	HttpSession session;

	/*
	 * オブジェクトをHTTPセッションに追加する
	 */
	@ModelAttribute("member")
	private Member setMember() {
		return new Member();
	}

	//トップページへ遷移
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		//LoginUser loginUser = (LoginUser) session.getAttribute("user");
		mav.setViewName("top");
		//mav.setViewName("header");
		mav.addObject("msg", "検索ワードを入力してください。");
		return mav;
	}

	//検索結果ページへ遷移
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView send(@RequestParam("text1") String str,
			@ModelAttribute("user") LoginUser loginUser,//SessionにsetAttributeしたオブジェクト取得できない
			ModelAndView mav) {
		//LoginUser loginUser = (LoginUser) session.getAttribute("user");
		mav.addObject("value", str);
		List<Member> list;
		if (str.equals("")) {
			list = repository.findByIdIsNotNullOrderByIdAsc();
		} else {
			//			list = repository.findByNameLike("%"+str+"%");
			list = repository.findByNameLikeOrAddressLike("%" + str + "%", "%" + str + "%");
		}
		mav.addObject("data", list);
		mav.setViewName("result2");
		return mav;
	}

	//	public ModelAndView index(@PathVariable int num,
	//			ModelAndView mav) {
	//メンバ詳細ページへ遷移
	@RequestMapping("/select")
	public ModelAndView index(@RequestParam("num") long lnum,
			@ModelAttribute Member member,
			ModelAndView mav) {
		Optional<Member> mem = repository.findById(lnum);
		Member m = mem.get();
		//member= mem.get();//memberの参照先変わるのでNG
		member.setId(m.getId());
		member.setName(m.getName());
		member.setAge(m.getAge());
		member.setMail(m.getMail());
		member.setAddress(m.getAddress());
		member.setTel(m.getTel());
		member.setMemo(m.getMemo());
		//member.clone(m);
		mav.addObject("member1", m);
		mav.setViewName("selected");
		return mav;
		//return new ModelAndView("redirect:/test");
	}

	//テスト用
	@RequestMapping("/test")
	public ModelAndView index(@ModelAttribute Member member,
			ModelAndView mav) {
//		System.out.println("member:" + member.getId());
//		System.out.println("member:" + member.getName());

		Optional<Member> mem = repository.findById((long)1);
		Member m = mem.get();

		List<MsgData> list = mrepository.findAll();
		MsgData ms = list.get(0);


		return new ModelAndView("redirect:/t");
	}

	//メンバ編集・削除
	@RequestMapping(value = "/editdelete", method = RequestMethod.POST)
	public ModelAndView editdelete(@ModelAttribute("member") Member member,
			@RequestParam("button") String btn,
			ModelAndView mav) {
		if(btn.equals("編集")) {
			mav.setViewName("edit");
		}else if(btn.equals("削除")){//削除の時
			repository.deleteById(member.getId());
			List<Member> list = repository.findByIdIsNotNullOrderByIdAsc();
			mav.addObject("data", list);
			mav.setViewName("result");
		}else {//コメント追加の時
			MsgData msgdata = new MsgData();
			mav.addObject("formModel", msgdata);
			mav.setViewName("comment");
		}
		return mav;
	}

	//メンバ更新
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView update(@ModelAttribute("member") Member member,
			ModelAndView mav) {
		repository.saveAndFlush(member);
		List<Member> list = repository.findByIdIsNotNullOrderByIdAsc();
		mav.addObject("data", list);
		mav.setViewName("result");
		return mav;
	}

	//コメント追加
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public ModelAndView msgform(
			@Valid @ModelAttribute MsgData msgdata,
			Errors result,
			@ModelAttribute("member") Member member,
			ModelAndView mav) {
		if (result.hasErrors()) {
			mav.setViewName("comment");
			mav.addObject("title", "Sample [ERROR]");
			mav.addObject("msg", "値を再チェックしてください!");
			return mav;
		} else {
			msgdata.setMember(member);
			msgdata.setDatetime(new Date());
			msgdata.setLoginUser((LoginUser) session.getAttribute("user"));
			mrepository.saveAndFlush(msgdata);
			String redirectStr = "redirect:/select?num=" + member.getId();
			return new ModelAndView(redirectStr);
		}
	}

	//新規メンバ登録コントロール
	@RequestMapping(value = "/touroku", method = RequestMethod.GET)
	public ModelAndView touroku(
			//@ModelAttribute("formModel") Member member,←これ入れるとSessionのMemberオブジェクトと思い格納されているキーと違うのでエラー
			ModelAndView mav) {

		mav.setViewName("touroku");
		mav.addObject("msg1", "メンバ登録");
		Member member = new Member();//なので自分でローカルでMemberオブジェクトを作り
		mav.addObject("formModel", member);//"formModel"という名前で登録（Modelにデータを追加）
		Iterable<Member> list = repository.findByIdIsNotNullOrderByIdDesc();
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value = "/touroku", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView form(
			@ModelAttribute("formModel")
			@Validated Member member,//リクエストスコープに"formModel"という名前でMemberオブジェクト入ってるのでOK
			BindingResult result,
			ModelAndView mav) {

		ModelAndView res = null;
		if (!result.hasErrors()){
			repository.saveAndFlush(member);
			res = new ModelAndView("redirect:/touroku");
		} else {
			mav.setViewName("touroku");
			mav.addObject("msg1", "<font color='red'>下記のエラーが発生しました</font>");
			//Member member = new Member();//なので自分でローカルでMemberオブジェクトを作り
			//mav.addObject("formModel", member);//"formModel"という名前で登録（たぶんリクエストスコープ）
			Iterable<Member> list = repository.findByIdIsNotNullOrderByIdDesc();
			mav.addObject("datalist", list);
			res = mav;
		}
		return res;

	}

}
