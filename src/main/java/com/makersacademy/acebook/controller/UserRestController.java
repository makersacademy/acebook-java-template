package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.dao.UserDAO;
import com.makersacademy.acebook.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

  private final UserDAO userDAO;

  public UserRestController(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @GetMapping("/users")
  Iterable<User> all() {
    return userDAO.findAll();
  }
}
