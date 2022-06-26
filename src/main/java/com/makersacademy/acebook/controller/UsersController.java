package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UsersController {

  @Autowired
  UserRepository userRepository;
  @Autowired
  AuthoritiesRepository authoritiesRepository;
  @Autowired
  PasswordEncoder getPasswordEncoder;

  @GetMapping("/users/new")
  public String signup(Model model) {
    model.addAttribute("user", new User());
    return "users/new";
  }

  @PostMapping("/users")
  public RedirectView signup(@ModelAttribute User user) {
    user.setPassword(getPasswordEncoder.encode(user.getPassword()));
    user.setUsername(user.getEmail());
    userRepository.save(user);
    Authority authority = new Authority(user.getUsername(), "ROLE_USER");
    authoritiesRepository.save(authority);
    return new RedirectView("/login");
  }

  @GetMapping("/users/all")
  public String all(Model model) {
    model.addAttribute("users", userRepository.findAll());
    return "users/all";
  }

  @GetMapping("/users/{username}")
  public String profile(Model model, @PathVariable String username) {
    User user = userRepository.findByUsername(username).get(0);
    model.addAttribute("user", user);
    return "users/profile";
  }

  @GetMapping("/users/search")
  public String showSearchResults(Model model, String keyword) {
    List<User> users = userRepository.findByUsernameContains(keyword);
    model.addAttribute("users", users);
    return "/users/search";
  }
}
