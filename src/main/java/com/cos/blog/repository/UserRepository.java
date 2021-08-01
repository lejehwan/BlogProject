package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

// 자동으로 Bean 등록이 됨 -> @Repository 생략 가능
public interface UserRepository extends JpaRepository<User,Integer> {

    // select * from user where username= 1?;
    Optional<User> findByUsername(String username);
}

//JPA Naming 쿼리
// select * from user where username =?1 and password =?2
//    User findByUsernameAndPassword(String username, String password);

//    #2 위와 같은 방법
//    @Query(value = "select * from user where username =?1 and password =?2", nativeQuery = true)
//    User login(String username, String password);