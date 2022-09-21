package com.makersacademy.acebook.controller;

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
  
  @GetMapping("/profile")
  public String profile(Model model) {
    return "profile/profile";
  }
}
