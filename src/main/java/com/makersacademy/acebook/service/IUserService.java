package com.makersacademy.acebook.service;
import com.makersacademy.acebook.model.User;

public interface IUserService {
  Boolean save(User user);
  Boolean usernameExists(String username);
  User findByUsername(String username);
}
