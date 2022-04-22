package com.makersacademy.acebook.controller;

import java.util.Optional;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LikesController {

  @Autowired
  LikeRepository repository;
  @Autowired
  PostRepository postRepository;

  // @GetMapping("/likes")
  // public String index(Model model) {
  //   Iterable<Post> posts = postRepository.findAllByOrderByTimestampDesc();
  //   // Long numberOfLikes = likeRepository.countByPostid(new Post().getId());
  //   model.addAttribute("posts", posts);
  //   model.addAttribute("post", new Post());
  //   // model.addAttribute("numberOfLikes", numberOfLikes);
  //   model.addAttribute("like", new Like());
  //   // model.addAttribute("user", )
  //   return "likes/index";
  // }
  
  // // @GetMapping("/likes/{id}")
  // // public String showLikes(@PathVariable("id") long id, Model model) {
  // //   Optional<Post> result = postRepository.findById(id);
  // //   Post post = result.get();
  // //   Long numberOfLikes = repository.countByPostid(id);
  // //   model.addAttribute("numberOfLikes", numberOfLikes);
  // //   model.addAttribute("post", post);
  // //   return "posts/index";
  // // }


  @PostMapping("/likes")
  public RedirectView likePost(@ModelAttribute Like like) {
    repository.save(like);
    return new RedirectView("/posts");
  }

  
}
