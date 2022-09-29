package com.makersacademy.acebook.controller;

import javax.servlet.http.HttpSession;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.Friendship;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.repository.FriendshipRepository;
import com.makersacademy.acebook.services.FriendsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
//ProfileController is apparently already a defined bean. so can't use it
public class MyProfileController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  FriendshipRepository friendshipRepository;

  @Autowired
  FriendsService friendsService;
  
  // @GetMapping("/profile")
  // public String myProfile(Model model, HttpSession session) {
  //   // Get (session) user ID
  //   Long userId = Long.parseLong(session.getAttribute("id").toString());
  //   model.addAttribute("id", userId);

  //   Optional<User> user = userRepository.findById(userId);
  //   model.addAttribute("user", user.get());

  //   return "profile/profile";
  // }
    
  @GetMapping("/profile/{username}")
  public String userProfile(@PathVariable String username, Model model, HttpSession session) {
    // Get (session) user ID
    Long userId = Long.parseLong(session.getAttribute("id").toString());
    model.addAttribute("id", userId);

    // Get non-blocked users (for search bar)
    model.addAttribute("allusers", userRepository.getNonBlockedUsers(userId));

    User user = userRepository.findByUserName(username);
    model.addAttribute("user", user);
    model.addAttribute("friendsservice", friendsService);

    return "profile/profile";
  }

  @PostMapping("/profile/{username}/request")
  public RedirectView friendRequestRespond(String requesterId, String requesteeId, String response) {
    Long reqerId = Long.valueOf(requesterId);
    Long reqeeId = Long.valueOf(requesteeId);
    Long friendshipId = null;
    Friendship friendship = new Friendship(reqerId, reqeeId);
    switch (response) {
      case "request":
        Long lowId = Math.min(reqerId, reqeeId);
        Long highId = Math.max(reqerId, reqeeId);
        userRepository.addFriendRequest(reqerId, reqeeId, lowId, highId);
        break;
      case "accept":
        userRepository.addAsFriends(reqerId, reqeeId);
        break;
      case "cancel":
        friendshipId = userRepository.getFriendshipId(reqerId, reqeeId);
        friendshipRepository.deleteById(friendshipId);
        break;
      case "unfriend":
        friendshipId = userRepository.getFriendshipId(reqerId, reqeeId);
        friendshipRepository.deleteById(friendshipId);
        break;
      case "block":
        // If not entries in 
        if (userRepository.getFriendshipId(reqerId, reqeeId) == null) {
          friendship.setRequestStatus("blocked");
          friendshipRepository.save(friendship);
        } else {
          friendshipId = userRepository.getFriendshipId(reqerId, reqeeId);
          friendshipRepository.deleteById(friendshipId);
          friendship.setRequestStatus("blocked");
          friendshipRepository.save(friendship);
        }
        return new RedirectView("/posts");
    }
    return new RedirectView("/profile/{username}");
  }
}
