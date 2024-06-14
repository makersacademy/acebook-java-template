package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Arrays;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

//    Arrays findByUserId(Long currentUserId);
}
