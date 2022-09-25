package com.makersacademy.acebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.Friendship;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.repository.FriendshipRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

@Controller
public class FriendsController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  FriendshipRepository friendshipRepository;

  @GetMapping("/friends")
  public String friends(Model model, HttpSession session) {

    // Add user ID as TL variable
    Long userID = Long.parseLong(session.getAttribute("id").toString());
    model.addAttribute("id", userID);
    User currentUser = userRepository.findById(userID).get();
    model.addAttribute("currentuser", currentUser);

    // Get friends and friend requests lists and add to TL as lists
    model.addAttribute("friend", new User());
    Iterable<User> friendsList = userRepository.getFriends(userID);
    model.addAttribute("friends", friendsList);
    // Iterable<User> requestsList = userRepository.getFriendRequests(userID);
    // model.addAttribute("reqs", requestsList);
    Iterable<Friendship> requestsList = friendshipRepository.getPendingFriendships(userID);
    model.addAttribute("reqs", requestsList);

    return "friends/friends";
  }

  // This adds a friend request
  @PostMapping("/friends/request")
  public RedirectView request(String requesterId, String requesteeId) {
    Long reqerId = Long.valueOf(requesterId);
    Long reqeeId = Long.valueOf(requesteeId);
    friendshipRepository.save(new Friendship(reqerId, reqeeId));
    return new RedirectView("/friends");
  }

  // This responds to a friend request/blocks another user
  @PostMapping("/friends")
  public RedirectView respond(String requesterId, String requesteeId, String response) {
    if (response == "accept") {
      Long reqerId = Long.valueOf(requesterId + "L");
      Long reqeeId = Long.valueOf(requesteeId + "L");
      userRepository.addAsFriends(reqerId, reqeeId);
    } else if (response == "reject") {

    }
    return new RedirectView("/friends");
  }
}
