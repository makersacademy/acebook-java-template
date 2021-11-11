package com.makersacademy.acebook.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class LikeController {
  @Autowired
  LikeRepository likeRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PostRepository postRepository;

  @GetMapping("/like")
  public RedirectView index() {
    return new RedirectView("/posts");
  }

  @PostMapping("/posts/{id}/likes")
  public RedirectView like(@PathVariable("id") Long id, Principal principal) {
    User thisUsers = userRepository.findByUsername(principal.getName()).get(0);
    Like like = new Like(thisUsers.getId(), id);
    likeRepository.save(like);
    return new RedirectView("/posts");
  }

}