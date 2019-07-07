package com.bitcamp.web.repositories;

import com.bitcamp.web.entities.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    // 로그인
    public User findUserByEmailIdAndPassword(String id, String password);

    // 마이페이지 접근시 email아이디로 접근
    public User findUserByEmailId(String emailId);
}