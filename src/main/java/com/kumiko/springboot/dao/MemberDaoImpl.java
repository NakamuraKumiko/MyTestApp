package com.kumiko.springboot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.kumiko.springboot.entity.Member;

@Repository
public class MemberDaoImpl implements MemberDao<Member> {

	@PersistenceContext
	private EntityManager entityManager;

	public MemberDaoImpl() {
		super();
	}

//	public MemberDaoImpl(EntityManager entityManager) {
//		super();
//		this.entityManager = entityManager;
//	}

	@Override
	public List<Member> getAll() {
		Query query = entityManager.createQuery("from Member");
		@SuppressWarnings("unchecked")
		List<Member> list = query.getResultList();
		entityManager.close();
		return list;
	}



	@Override
	public Member findById(long id) {
		return (Member)entityManager.createQuery("from Member where id = "
				+ id).getSingleResult();
	}

	@Override
	public List<Member> findByName(String name) {
		return (List<Member>)entityManager.createQuery("from Member where name like '%"
				+ name+"%'").getResultList();
	}



}
