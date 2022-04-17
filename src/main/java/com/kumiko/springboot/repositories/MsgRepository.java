package com.kumiko.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kumiko.springboot.entity.MsgData;



@Repository
public interface MsgRepository extends JpaRepository<MsgData, Long>{

}
