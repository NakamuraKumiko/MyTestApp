package com.kumiko.springboot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.javafaker.Faker;
import com.kumiko.springboot.entity.LoginUser;
import com.kumiko.springboot.entity.Member;
import com.kumiko.springboot.entity.MsgData;
import com.kumiko.springboot.repositories.LoginUserRepository;
import com.kumiko.springboot.repositories.MemberRepository;
import com.kumiko.springboot.repositories.MsgRepository;

//@Controller
public class InitController {

	@Autowired
	MemberRepository repository;

	@Autowired
	MsgRepository mrepository;

	@Autowired
	LoginUserRepository lrepository;

	Faker dataGenerator = new Faker(Locale.JAPAN, new Random(1));

	ArrayList<Member> list = new ArrayList<>();
	ArrayList<LoginUser> llist = new ArrayList<>();

	//memberテーブルへ初期データ挿入
	@PostConstruct
	public void init() {
		for (int i = 30; i < 40; i++) {
			Member member = new Member();
			member.setName(dataGenerator.name().fullName());
			member.setMail(dataGenerator.bothify("????##@gmail.com"));
			member.setAddress(dataGenerator.address().fullAddress());
			member.setTel(String.valueOf((int) (Math.random() * Math.pow(10, 9))));
			member.setAge(i);
			member.setMemo("memo example");
			repository.saveAndFlush(member);
			//member.setId(0);//memberインスタンス使いまわしする時主キーをクリア
			if (i < 35) {
				list.add(member);
			}
		}

		//loginテーブルへ初期データ挿入
		for (int i = 0; i < 5; i++) {
			LoginUser luser = new LoginUser();
			luser.setLoginId("S00"+i);
			luser.setName(dataGenerator.name().fullName());
			luser.setPass("password");

			lrepository.saveAndFlush(luser);
			llist.add(luser);

		}

	}

	//loginテーブルへ初期データ挿入
	/*	@PostConstruct
		public void init2() {
			for (int i = 0; i < 5; i++) {
				LoginUser luser = new LoginUser();
				luser.setLoginId("S00"+i);
				luser.setName(dataGenerator.name().fullName());
				luser.setPass("password");

				lrepository.saveAndFlush(luser);
				llist.add(luser);

			}
		}*/

	//msgテーブルへ初期データ挿入
	@PostConstruct
	public void init3() {

		for (Member m : list) {
			//System.out.println(m);
			for (int i = 0; i < 3; i++) {
				MsgData msg = new MsgData();
				msg.setTitle(dataGenerator.lorem().word());
				msg.setMessage(dataGenerator.lorem().word());
				msg.setDatetime(new Date());
				msg.setMember(m);
				msg.setLoginUser(llist.get(i));

				mrepository.saveAndFlush(msg);

			}

		}

	}
}
