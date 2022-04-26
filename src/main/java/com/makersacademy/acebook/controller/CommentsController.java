package com.makersacademy.acebook.controller;

import java.security.Principal;

import com.makersacademy.acebook.model.Comment;
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

  @PostMapping("/comments")
  public RedirectView likePost(@ModelAttribute Comment comment, Principal principal) {
    String username = principal.getName();
    Long userid = userRepository.findIdByUsername(username);
    comment.setUserid(userid);
    comment.generateTimestamp();
    commentRepository.save(comment);
    return new RedirectView("/posts");
  }


}
