package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.FriendRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import com.makersacademy.acebook.model.Post;

// Image upload
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ClassPathResource;

@Controller
public class FriendController {

  @Autowired
  FriendRepository friendRepository;
  @Autowired
  UserRepository userRepository;
  @Autowired
  AuthoritiesRepository authoritiesRepository;
  @Autowired
  PasswordEncoder getPasswordEncoder;
  @Autowired
  PostRepository postRepository;

  //Password authentication
  Boolean matchingPassword = true;

  @GetMapping("/friends/{username}")
  public String profile(Model model, @PathVariable String username) {
    User user = userRepository.findByUsername(username).get(0);
    Long userId = user.getId();
    
    List<Friend> friends = 
    
    model.addAttribute("user", user);
    return "users/profile";
  }
  
}
