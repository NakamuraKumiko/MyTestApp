package com.kumiko.springboot.dao;

import java.io.Serializable;
import java.util.List;

public interface MemberDao <T> extends Serializable {

	public List<T> getAll();
	public T findById(long id); // ●
	public List<T> findByName(String name); // ●
}
