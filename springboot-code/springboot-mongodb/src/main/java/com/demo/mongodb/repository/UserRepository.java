package com.demo.mongodb.repository;

import com.demo.mongodb.pojo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author wangxing
 * @date 2024-08-12 19:42
 */
public interface UserRepository extends MongoRepository<User, Long> {

    User findByUsername(String username);

}
