package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.Post;
import org.springframework.web.bind.annotation.RequestMapping;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class UserPageController {

  @Autowired
  UserRepository userRepository;
  @Autowired
  AuthoritiesRepository authoritiesRepository;
  @Autowired
  PostRepository repository;
  

  
}
