package com.kumiko.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kumiko.springboot.entity.UserCommentNum;

@Repository
public interface UserCommentNumRepository extends JpaRepository<UserCommentNum, Long>  {
    public static final String _query
     = "select min(login_user.id) as id,name,count(message) from login_user left join "
     		+ "msgdata on login_user.id = msgdata.login_user_id group by name order by count DESC"
     ;

    @Query(value = _query, nativeQuery = true)
    List<UserCommentNum> findUserCommentNum();


}

