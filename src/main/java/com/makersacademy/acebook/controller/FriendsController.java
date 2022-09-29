package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.FriendsRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class FriendsController {

 @Autowired
 FriendsRepository repository;

 @Autowired
 UserRepository userRepository;

 private Long getUserId() {
  SecurityContext context = SecurityContextHolder.getContext();
  Authentication authentication = context.getAuthentication();
  Long id = userRepository.findByUsername(authentication.getName()).getId();
  return id;
 }

 private String getUsername() {
  SecurityContext context = SecurityContextHolder.getContext();
  Authentication authentication = context.getAuthentication();
  String name = authentication.getName();
  return name;
 }

 @GetMapping("/friends")
 public String friends(Model model) {
  Iterable<Object[]> friends = repository.findFriends(getUserId());
  model.addAttribute("friends", friends);
  model.addAttribute("friend", new Friend());
  return "friends";
 }

 @PostMapping("/users/addfriend")
 public RedirectView sendRequest(@RequestParam("userid") Long receiveid, @ModelAttribute Friend friend) {
  try {
   friend.setRequest_to(receiveid);
   friend.setRequest_from(getUserId());
   friend.setStatus_code(false);
   repository.save(friend);

  } catch (Exception e) {
   System.out.println(e);
  }

  return new RedirectView("/allUsers");
 }

 @GetMapping("/friends/requests")
 public String friendRequest(Model model) {
  Iterable<Object[]> pendingFriends = repository.pendingFriendsTo(getUserId());
  model.addAttribute("requests", pendingFriends);

  return "request/index";
 }

 @RequestMapping("/request/acceptFriend")
 @ResponseBody
 public RedirectView acceptFriendRequest(@RequestParam("requestid") Long request_id) {
  try {

   Friend request = repository.findById(request_id).get();
   request.setStatus_code(true);
   repository.save(request);

  } catch (Exception e) {
   System.out.println(e);
  }
  return new RedirectView("/friends");
 }

 @RequestMapping("/request/declineFriend")
 @ResponseBody
 public RedirectView declineFriendRequest(@RequestParam("requestid") Long request_id) {
  try {

   repository.deleteById(request_id);

  } catch (Exception e) {
   System.out.println(e);
  }
  return new RedirectView("/friends");
 }
}
