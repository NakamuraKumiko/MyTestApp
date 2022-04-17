package com.kumiko.springboot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kumiko.springboot.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	public Optional<Member> findById(Long id);
	public List<Member> findByNameLike(String name);
	public List<Member> findByIdIsNotNullOrderByIdDesc();
	public List<Member> findByIdIsNotNullOrderByIdAsc();
	public List<Member> findByAgeGreaterThan(Integer age);
	public List<Member> findByAgeBetween(Integer age1, Integer age2);

	public List<Member> findByNameLikeOrAddressLike(String name,String address);
}

