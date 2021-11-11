package com.makersacademy.acebook.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.Instant;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CommentsController {

  @Autowired
  CommentRepository commentRepository;

  @Autowired
  UserRepository userRepository;

  @PostMapping("/posts/{id}/comments") // sample URL path for when adding comment to post 1: /posts/1/comments
  public RedirectView create(@PathVariable("id") Long id, Principal principal, @ModelAttribute Comment comment) {
    User user = userRepository.findByUsername(principal.getName()); // in Spring Boot Security magic, principal is the current user // Update UserRepository.java findByUsername
    comment.setCreatedAt(Timestamp.from(Instant.now())); // to fill the db gaps, we need to set Timestamp, user_id, post_id
    comment.setUserId(user.getId()); // in Comment.java, the user_id column isn't joined, so we get user ID via Principal
    comment.setPostId(id);
    commentRepository.save(comment);
    return new RedirectView("/posts");
  }
}
