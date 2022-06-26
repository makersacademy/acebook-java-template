package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class PostsController {

  @Autowired
  PostRepository postRepository;
  @Autowired
  UserRepository userRepository;

  @GetMapping("/posts")
  public String index(Model model, @ModelAttribute Post post, Principal principal) {
    List<Post> posts = postRepository.findAllByOrderByTimeDesc();
    model.addAttribute("posts", posts);
    model.addAttribute("principal", principal);
    return "posts/feed";
  }

  @PostMapping("/posts")
  public RedirectView create(@ModelAttribute Post post, Principal principal) {
    User user = userRepository.findByUsername(principal.getName()).get(0);
    post.setUser(user);
    postRepository.save(post);
    return new RedirectView("/posts");
  }
}
