package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;

  @GetMapping("/login")
  public String login(Model model) {
      model.addAttribute("showLogout", false);
      return "login/login";
  }

  @PostMapping("/login")
  public String post(Model model){return "posts"; }


    
}