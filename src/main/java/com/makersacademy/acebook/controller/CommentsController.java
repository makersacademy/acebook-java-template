package com.makersacademy.acebook.controller;

import java.security.Principal;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CommentsController {

  @Autowired
  CommentRepository commentRepository;
  @Autowired
  PostRepository postRepository;
  @Autowired
  UserRepository userRepository;

  private User getUser(Principal principal) {
    String username = principal.getName();
    User user = userRepository.findByUsername(username);
    return user;
  }

  @PostMapping("/comments")
  public RedirectView likePost(@ModelAttribute Comment comment, Principal principal) {
    comment.setUserid(getUser(principal).getId());
    comment.generateTimestamp();
    commentRepository.save(comment);
    return new RedirectView("/posts");
  }


}
