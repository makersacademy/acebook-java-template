package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String accountPage(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        User user = userService.findByUsername(currentUser.getUsername());
        model.addAttribute("user", user);
        return "account";
    }

    @PostMapping
    public String updateAccount(@AuthenticationPrincipal UserDetails currentUser, User updatedUser) {
        User user = userService.findByUsername(currentUser.getUsername());
        user.setUsername(updatedUser.getUsername());
        if (!updatedUser.getPassword().isEmpty()) {
            user.setPassword(updatedUser.getPassword()); // The password will be encoded in the service
        }
        user.setEmail(updatedUser.getEmail());
        user.setProfilePictureUrl(updatedUser.getProfilePictureUrl());
        userService.save(user);
        return "redirect:/account";
    }
}

