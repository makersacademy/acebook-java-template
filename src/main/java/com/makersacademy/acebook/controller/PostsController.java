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

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.List;

@Controller
public class PostsController {
  @Autowired
  PostRepository postRepository;
  @Autowired
  UserRepository userRepository;

  @GetMapping("/posts")
  public String index(Model model) {
    Iterable<Post> posts = postRepository.findAll();
    model.addAttribute("posts", posts);
    model.addAttribute("post", new Post());
    return "posts/index";
  }

  @PostMapping("/posts")
  public RedirectView create(@ModelAttribute Post post, Principal principal) {
    User user = userRepository.findByUsername(principal.getName()).get(0);
    post.setUserId(user.getId());
    postRepository.save(post);
    return new RedirectView("/posts");
  }
}
