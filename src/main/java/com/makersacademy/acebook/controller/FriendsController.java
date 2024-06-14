package com.makersacademy.acebook.controller;


import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.FriendRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Controller
public class FriendsController {

    @Autowired
    FriendRepository friendRepository;

    @Autowired
    UserRepository userRepository;


    // Endpoint to list friends
//    @GetMapping("/friends")
//    public String listFriends(Model model, Principal principal) {
//
//
//        User currentUser = userRepository.findByUsername(principal.getName());
//        List<Friend> friends = friendRepository.findByUser(currentUser);
////        model.addAttribute("currentUser", currentUser);
//        model.addAttribute("friends", currentUser.getFriends());
//        return "users/friendList"; // Returns the friendlist.html view
//    }

    @GetMapping("/friends")
    public String listFriends(Model model, Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName());
        List<User> friends = new ArrayList<>(currentUser.getFriends()); // Retrieve friends from the current user
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("friends", friends);
        return "users/friendList"; // Returns the friendList.html view
    }


    // Method to add friends
    @PostMapping("/addfriend")
    public RedirectView create(@RequestParam String friendUsername, @AuthenticationPrincipal Principal principal, RedirectAttributes redirectAttributes) {

        User currentUser = userRepository.findByUsername(principal.getName());
        User friendUser = userRepository.findByUsername(friendUsername);
        if (friendUser != null && !currentUser.equals(friendUser)) {
            Friend friend = new Friend();
            friend.setUser(currentUser);
            friend.setFriend(friendUser);
//            friend.setCreatedAt(new Date());
            friendRepository.save(friend);
            redirectAttributes.addFlashAttribute("message", "Friend added successfully.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid friend username.");
        }
        return new RedirectView("/friends");
    }



    // Endpoint to list all users
    @GetMapping("/all-users")
    public String listAllUsers(Model model, Principal principal) {
        User currentUser = userRepository.findByUsername(principal.getName());
        List<User> allUsers = (List<User>) userRepository.findAll();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("allUsers", allUsers);
        return "users/all-users"; // Returns the all-users.html view
    }






}
