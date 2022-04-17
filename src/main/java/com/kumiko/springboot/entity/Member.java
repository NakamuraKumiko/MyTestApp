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
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.kumiko.springboot.annotation.Phone;





//import org.hibernate.validator.constraints.Email;
//import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "member")
public class Member implements Serializable {

	// 以下のフィールドとメソッドを追加
	@OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
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
	@NotEmpty(message="名前は必ず入力してください")	// ●
	private String name;

	@Column(length = 100, nullable = true)
	@Email(message="正しいメール形式で入力してください")	// ●
	private String mail;

	@Column(length = 100, nullable = true)
	private String address;

	@Column(length = 20, nullable = true)
	@Phone(message="正しい電話番号を入力してください") // ●
	private String tel;

	@Column(nullable = true)
	@Min(value = 0,message="年齢は正の整数を入力してください")// ●
	@Max(value = 200,message="そんなに長生きの人はいませんよ") // ●
	private Integer age;

	@Column(nullable = true)
	//	@Phone(onlyNumber = true) // ●
	private String memo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
