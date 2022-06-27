package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.FriendRepository;
import com.makersacademy.acebook.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class FriendsController {

  @Autowired
  FriendRepository friendRepository;
  @Autowired
  UserRepository userRepository;

  //Password authentication
  Boolean matchingPassword = true;

  @GetMapping("/friends/{username}")
  public String profile(Model model, @PathVariable String username) {
    User user = userRepository.findByUsername(username).get(0);
    List<Friend> friends = friendRepository.findByUserIdAndAccepted(user.getId(), true);
    
    model.addAttribute("friends", friends);
    return "friends/list";
  }
  
}
