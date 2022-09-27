package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.FriendsRepository;
import com.makersacademy.acebook.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class InboxController {

 @Autowired
 FriendsRepository repository;

 @Autowired
 UserRepository userRepository;

 @GetMapping("/inbox")
 public String friends(Model model) {
  Iterable<User> friends = userRepository.findAll();
  model.addAttribute("friends", friends);
  model.addAttribute("friend", new Friend());
  return "inbox/index";
 }

}
