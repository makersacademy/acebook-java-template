package com.makersacademy.acebook.controller;

import java.security.Principal;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LikesController {

  @Autowired
  LikeRepository likeRepository;
  @Autowired
  PostRepository postRepository;
  @Autowired
  UserRepository userRepository;


  @PostMapping("/likes")
  public RedirectView likePost(@ModelAttribute Like like, Principal principal) {
    // String username = principal.getName();
    // Long userid = userRepository.findIdByUsername(username);
    // like.setUserid(userid);
    likeRepository.save(like);
    Iterable<Post> posts = postRepository.findAll();
    for (Post post : posts) {
      Long likes = likeRepository.countByPostid(post.getId());
      post.setLikes(likes);
      postRepository.save(post);
    }
    return new RedirectView("/posts");
  }

  @DeleteMapping("/likes/{id}")
  public RedirectView unlikePost(@PathVariable("id") Long id) {
    likeRepository.deleteById(id);
    Iterable<Post> posts = postRepository.findAll();
    for (Post post : posts) {
      Long likes = likeRepository.countByPostid(post.getId());
      post.setLikes(likes);
      postRepository.save(post);
    }
    return new RedirectView("/posts");
  }
 
}
