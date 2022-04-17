package com.kumiko.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kumiko.springboot.entity.LoginUser;

@Repository
public interface LoginUserRepository extends JpaRepository<LoginUser, Long>{
	public LoginUser findByLoginIdAndPass(String name,String pass);
}

