package com.makersacademy.acebook.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.services.FriendsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
  FriendsService friendsService;
  
  @GetMapping("/profile")
  public String myProfile(Model model, HttpSession session) {
    // Get (session) user ID
    Long userId = Long.parseLong(session.getAttribute("id").toString());
    model.addAttribute("id", userId);

    Optional<User> user = userRepository.findById(userId);
    model.addAttribute("user", user.get());

    return "profile/profile";
  }
    
  @GetMapping("/profile/{id}")
  public String userProfile(@PathVariable String id, Model model, HttpSession session) {
    // Get (session) user ID
    Long userId = Long.parseLong(session.getAttribute("id").toString());
    model.addAttribute("id", userId);

    Long otherId = Long.valueOf(id);
    Optional<User> user = userRepository.findById(otherId);
    model.addAttribute("user", user.get());
    model.addAttribute("friendsservice", friendsService);

    if (userId == otherId) {
      return "redirect:/showData";
    } else {
      return "profile/profile";
    }
  }
}
