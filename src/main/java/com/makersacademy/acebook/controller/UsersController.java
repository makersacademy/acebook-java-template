package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
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

// Image upload
import org.springframework.core.io.ClassPathResource;

@Controller
public class UsersController {

  @Autowired
  UserRepository userRepository;
  @Autowired
  PasswordEncoder getPasswordEncoder;
  // @Autowired
  // AuthoritiesRepository authoritiesRepository;

  // Password authentication
  Boolean matchingPassword = true;

  @GetMapping("/users/new")
  public String signup(Model model) {
    model.addAttribute("user", new User());
    return "users/new";
  }

  // this was user's profile
  @PostMapping("/users")
  public RedirectView signup(@ModelAttribute User user) {
    user.setPassword(getPasswordEncoder.encode(user.getPassword()));
    user.setUsername(user.getEmail());
    userRepository.save(user);
    return new RedirectView("/login");
  }

  @GetMapping("/users/all")
  public String all(Model model) {
    model.addAttribute("users", userRepository.findAll());
    return "users/all";
  }

  @GetMapping("users/settings")
  public String settings(Model model, Principal principal) {
    User currentUser = userRepository.findByUsername(principal.getName()).get(0);
    model.addAttribute("user", currentUser);
    return "/users/settings";
  }

  // @PostMapping("/posts")
  // public RedirectView create(@ModelAttribute Post post, Principal principal) {
  // User user = userRepository.findByUsername(principal.getName()).get(0);
  // post.setUserId(user.getId());
  // postRepository.save(post);
  // return new RedirectView("posts");
  // }

  @GetMapping("/users/editDetails")
  public String showDetails(Model model, Principal principal, @ModelAttribute User user) {
    User currentUser = userRepository.findByUsername(principal.getName()).get(0);
    model.addAttribute("user", currentUser);
    // Password authentication
    // model.addAttribute("matchingPassword", matchingPassword);

    // user.setName(currentUser.getName());
    // user.setAbout(currentUser.getAbout());
    // user.setEmail(currentUser.getEmail());

    // // Image upload
    // ClassPathResource imgFile = new
    // ClassPathResource("../resources/static/images/pepe.jpeg");

    return "/users/editDetails";
  }

  @PostMapping("/users/settings")
  public RedirectView saveDetails(@ModelAttribute User user) {
    user.setUsername(user.getEmail());
    if (user.getPassword().isEmpty()) {
      user.setPassword(user.getPassword());
    }
    userRepository.save(user);
    return new RedirectView("/users/settings");

  }

  // THEO STUFF
  @GetMapping("/users/{username}")
  public String profile(Model model, @PathVariable String username) {
    User user = userRepository.findByUsername(username).get(0);
    model.addAttribute("user", user);
    return "users/profile";
  }

  @GetMapping("/users/search")
  public String showSearchResults(Model model, String keyword) {
    List<User> users = userRepository.findByUsernameContains(keyword);
    model.addAttribute("users", users);
    return "/users/search";
  }
}
