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

import javax.servlet.http.HttpSession;

@Controller
public class FriendsController {

  @Autowired
  UserRepository userRepository;

  @GetMapping("/friends")
  public String friends(Model model, HttpSession session) {

    // Add user ID as TL variable
    Long userID = Long.parseLong(session.getAttribute("id").toString());
    model.addAttribute("id", userID);

    // Get friends and friend requests lists and add to TL as lists
    model.addAttribute("friend", new User());
    Iterable<User> friendsList = userRepository.getFriends(userID);
    model.addAttribute("friends", friendsList);
    Iterable<User> requestsList = userRepository.getFriendRequests(userID);
    model.addAttribute("reqs", requestsList);

    return "friends/friends";
  }

  // This adds friends directly for now
  @PostMapping("/friends")
  public RedirectView create(Long requesterId, Long requesteeId) {
    userRepository.addAsFriends(requesterId, requesteeId);
    return new RedirectView("/friends");
  }
}
