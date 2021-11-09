package com.makersacademy.acebook.repository;

import java.util.List;

import com.makersacademy.acebook.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    // List<User> findByUsername(String username);

}
