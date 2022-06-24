package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.User;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByUsername(String username);

}
