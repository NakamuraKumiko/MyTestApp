package com.kumiko.springboot.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//import org.hibernate.validator.constraints.Email;
//import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "loginUser")
public class LoginUser implements Serializable {

	// 以下のフィールドとメソッドを追加
	@OneToMany(mappedBy = "loginUser",cascade = CascadeType.ALL)
	@OrderBy("datetime DESC")
	@Column(nullable = true)
	private List<MsgData> msgdatas;

	public List<MsgData> getMsgdatas() {
		return msgdatas;
	}

	public void setMsgdatas(List<MsgData> msgdatas) {
		this.msgdatas = msgdatas;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@NotNull // ●
	private long id;

	@Column(length = 50, nullable = false)
	//@NotEmpty	// ●
	private String loginId;

	@Column(length = 15, nullable = false)
	//@NotEmpty	// ●
	private String name;

	@Column(length = 15, nullable = false)
	//@NotEmpty	// ●
	private String pass;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}


}
