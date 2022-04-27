package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
  // @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery=true)
  // public User findByUsername(@Param("username") String username);
  User findByUsername(String username);
}
