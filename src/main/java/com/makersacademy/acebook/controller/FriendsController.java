package com.makersacademy.acebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.makersacademy.acebook.services.FriendsService;

import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.Friendship;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.repository.FriendshipRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
public class FriendsController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  FriendshipRepository friendshipRepository;

  @Autowired
  FriendsService friendsService;

  @GetMapping("/friends")
  public String friends(Model model, HttpSession session) {
    // Get (session) user ID
    Long userId = Long.parseLong(session.getAttribute("id").toString());
    model.addAttribute("id", userId);
    User currentUser = userRepository.findById(userId).get();
    model.addAttribute("currentuser", currentUser);

    ///

    // Get user object
    model.addAttribute("friend", new User());

    ///

    // Get all users (for search bar)
    model.addAttribute("allusers", userRepository.getNonBlockedUsers(userId));

    ///

    // Add MutualFriends service (to call methods in TL)
    model.addAttribute("friendsservice", friendsService);

    ///

    // Get friends
    Iterable<User> friends = userRepository.getFriends(userId);
    // Count friends, only return if not 0. Send count to TL for "You haven't made
    // any friends yet." message conditional
    int friendCount = 0;
    // (Need user for enhanced for loop, even though it "is not used")
    for (User user : friends) {
      friendCount++;
    }
    model.addAttribute("friendcount", friendCount);
    if (friendCount > 0) {
      model.addAttribute("friends", friends);
    }

    ///

    // Get incoming friend requests
    Iterable<User> incomingReqs = userRepository.getIncomingFriendRequests(userId);
    // Count incoming requests, only return if not 0. Send count to TL for "No
    // incoming requests" message conditional
    int inReqCount = 0;
    // (Need user for enhanced for loop, even though it "is not used")
    for (User user : incomingReqs) {
      inReqCount++;
    }
    model.addAttribute("incount", inReqCount);
    if (inReqCount > 0) {
      model.addAttribute("inreqs", incomingReqs);
    }

    ///

    // Get outgoing friend requests
    Iterable<User> outgoingReqs = userRepository.getOutgoingFriendRequests(userId);
    // Count outgoing requests, only return if not 0. Send count to TL for "No
    // outgoing requests" message conditional
    int outReqCount = 0;
    // (Need user for enhanced for loop, even though it "is not used")
    for (User user : outgoingReqs) {
      outReqCount++;
    }
    model.addAttribute("outcount", outReqCount);
    if (outReqCount > 0) {
      model.addAttribute("outreqs", outgoingReqs);
    }

    ///

    // Get new users (strangers for browse users)
    Iterable<User> strangers = userRepository.getStrangers(userId);
    // Count strangers, only return if not 0. Send count to TL for "No outgoing
    // requests" message conditional
    int strangerCount = 0;
    // (Need user for enhanced for loop, even though it "is not used")
    for (User user : strangers) {
      strangerCount++;
    }
    model.addAttribute("strangercount", strangerCount);
    if (strangerCount > 0) {
      model.addAttribute("strangers", strangers);
    }

    ///

    // Get friends of friends (potential connections)
    Iterable<User> friendsOfFriends = userRepository.getFriendsOfFriends(userId);
    // Count friends of friends, only return if not 0. Send count to TL for "No
    // outgoing requests" message conditional
    int friendsOfFriendsCount = 0;
    // (Need user for enhanced for loop, even though it "is not used")
    for (User user : friendsOfFriends) {
      friendsOfFriendsCount++;
    }
    model.addAttribute("friendsfriendscount", friendsOfFriendsCount);
    if (friendsOfFriendsCount > 0) {
      model.addAttribute("friendsfriends", friendsOfFriends);
    }

    ///

    // Get users blocked by current user (blocked users)
    Iterable<User> blockedUsers = userRepository.getBlockedUsers(userId);
    // Count blocked users, only return if not 0. Send count to TL for "No blocked
    // users" message conditional
    int blockedUsersCount = 0;
    // (Need user for enhanced for loop, even though it "is not used")
    for (User user : blockedUsers) {
      blockedUsersCount++;
    }
    model.addAttribute("blockedcount", blockedUsersCount);
    if (blockedUsersCount > 0) {
      model.addAttribute("blockedusers", blockedUsers);
    }

    ///

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
    Long reqerId = Long.valueOf(requesterId);
    Long reqeeId = Long.valueOf(requesteeId);
    Long friendshipId = null;
    Friendship friendship = new Friendship(reqerId, reqeeId);
    switch (response) {
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
        break;
      case "unblock":
        if (userRepository.getRequestStatus(reqerId, reqeeId).equals("blocked")) {
          friendshipId = userRepository.getFriendshipId(reqerId, reqeeId);
          friendshipRepository.deleteById(friendshipId);
        }
        break;
    }
    return new RedirectView("/friends");
  }
}
