package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.User;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
   User findByUsername(String username);
}
