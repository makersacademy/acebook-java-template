package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.User;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
  public Optional<User> findByUsername(String username);
  public Optional<User> findById(Long id);
}
