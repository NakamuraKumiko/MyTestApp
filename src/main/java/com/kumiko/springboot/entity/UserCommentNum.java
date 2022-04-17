package com.kumiko.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserCommentNum {

	@Id
	private long id;
    private String name;
    private long count;


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
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}

}


