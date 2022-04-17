package com.kumiko.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kumiko.springboot.entity.UserComment;

@Repository
public interface UserCommentRepository extends JpaRepository<UserComment, Long>  {
    public static final String _query
     = "select msgdata.id,name,title,message from login_user join msgdata on login_user.id = msgdata.login_user_id"
     ;

    public static final String _query2
    = "select msgdata.id,name,title,message from login_user left join msgdata on login_user.id = msgdata.login_user_id"
    ;

    @Query(value = _query, nativeQuery = true)
    List<UserComment> findUserComment();

    @Query(value = _query, nativeQuery = true)
    List<UserComment> findUserComment2();

}

