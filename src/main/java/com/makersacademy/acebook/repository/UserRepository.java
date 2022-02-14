package com.makersacademy.acebook.repository;

import java.util.List;

import com.makersacademy.acebook.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  boolean existsUserByUsername(String fakeUser);
  List<User> findByUsername(String username);

}

