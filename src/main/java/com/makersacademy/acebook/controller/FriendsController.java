package com.makersacademy.acebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Controller
public class FriendsController {

  @Autowired
  UserRepository userRepository;

  @GetMapping("/friends")
  public String friends(Model model) {
    // model.addAttribute(attributeValue);
    // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    // User currentUser = userService.getUser(userDetail.getUsername());
    //     request.getSession().setAttribute("userId", u.getId());
    // // int currentUserID = currentUser.getUserID();
    // userRepository.getFriends();
    return "friends/friends";
  }
}
