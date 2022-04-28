package com.makersacademy.acebook.controller;

import java.security.Principal;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.Post;
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
    comment.setUser(getUser(principal));
    comment.generateTimestamp();
    if (comment.getContent() != "") {
      commentRepository.save(comment);
    }
    Iterable<Post> posts = postRepository.findAll();
    for (Post post : posts) {
      Long commentcount = commentRepository.countByPostid(post.getId());
      post.setCommentcount(commentcount);
      postRepository.save(post);
    }
    return new RedirectView("/posts");
  }

}
