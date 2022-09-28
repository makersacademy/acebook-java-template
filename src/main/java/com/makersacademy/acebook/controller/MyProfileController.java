package com.makersacademy.acebook.controller;

import javax.servlet.http.HttpSession;
import com.makersacademy.acebook.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
//ProfileController is apparently already a defined bean. so can't use it
public class MyProfileController {

  @Autowired
  UserRepository userRepository;
  
  @GetMapping("/profile")
  public String profile(Model model, HttpSession session) {
    // Get (session) user ID
    Long userID = Long.parseLong(session.getAttribute("id").toString());
    model.addAttribute("id", userID);

    return "profile/profile";
  }
}
