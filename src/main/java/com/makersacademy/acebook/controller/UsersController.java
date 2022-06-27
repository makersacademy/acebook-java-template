package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
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
public class UsersController {

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

  @GetMapping("/users/new")
  public String signup(Model model) {
    model.addAttribute("user", new User());
    return "users/new";
  }

  //this was user's profile
  @PostMapping("/users")
  public RedirectView signup(@ModelAttribute User user) {
    user.setPassword(getPasswordEncoder.encode(user.getPassword()));
    user.setUsername(user.getEmail());
    user.setAbout(user.getAbout());
    userRepository.save(user);
    Authority authority = new Authority(user.getUsername(), "ROLE_USER");
    authoritiesRepository.save(authority);
    return new RedirectView("/login");
  }

  @GetMapping("/users/all")
  public String all(Model model) {
    model.addAttribute("users", userRepository.findAll());
    return "users/all";
  }

  @GetMapping("users/settings")
  public String settings(Model model, Principal principal){
    User currentUser = userRepository.findByUsername(principal.getName()).get(0);
    model.addAttribute("user", currentUser);
    return "/users/settings";
  }

  @PostMapping("/posts")
  public RedirectView create(@ModelAttribute Post post, Principal principal) {
      User user = userRepository.findByUsername(principal.getName()).get(0);
      post.setUserId(user.getId());
      postRepository.save(post);
      return new RedirectView("posts");
  }

  @GetMapping("/users/editDetails")
  public String showDetails(Model model, Principal principal, @ModelAttribute User user){
    User currentUser = userRepository.findByUsername(principal.getName()).get(0);
    model.addAttribute("user", currentUser);
    // Password authentication 
    model.addAttribute("matchingPassword", matchingPassword);

    user.setName(currentUser.getName());
    user.setAbout(currentUser.getAbout());
    user.setEmail(currentUser.getEmail());

    //Image upload
    ClassPathResource imgFile = new ClassPathResource("../resources/static/images/pepe.jpeg");
    model.addAttribute(imgFile);

    return "/users/editDetails";
  }

  @PostMapping("/users/settings")
  public RedirectView saveDetails(
    @RequestParam(value = "name", required = false) String name,
    @RequestParam(value = "about", required = false) String about,
    @RequestParam(value = "email", required = false) String email,
    @RequestParam(value = "password1", required = false) String password1,
    @RequestParam(value = "password2", required = false) String password2,
    
    //@RequestParam(value = "imageUrl", required = false) String imageUrl,
    @RequestParam(value = "image", required = false) MultipartFile multipartFile,

    Principal principal,
    @ModelAttribute User user) {
      user = userRepository.findByUsername(principal.getName()).get(0);
      user.setName(name);
      user.setAbout(about);
      user.setEmail(email);
      //String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
      // Password authentication. It only checks the first password as both of them needs to be filled in order to be changed.
      if (!password1.isEmpty() && password1==password2){
        matchingPassword = true;
        user.setPassword(getPasswordEncoder.encode(user.getPassword()));
        // If they navigate away from the site the matchinpassword boolean remains false which is stupid. revisiting later.
      } else if (!password1.isEmpty() || !password2.isEmpty()) {
          matchingPassword = false;
          return new RedirectView("/users/editDetails");
      }
      userRepository.save(user);
      return new RedirectView("/users/settings");

    }
}
