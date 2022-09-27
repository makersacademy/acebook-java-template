package com.makersacademy.acebook.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.view.RedirectView;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

@Controller
public class CommentsController {
  
  @Autowired
  UserRepository userRepository;

  @Autowired
  PostRepository postRepository;

  @Autowired
  CommentRepository commentRepository;

  @GetMapping("/posts/{postID}/comments") 
    public RedirectView create(@ModelAttribute Post post, Principal principal, Model model) {
      String username = principal.getName();
      User user = userRepository.findByUserName(username);
      Long postID = post.getId();

      // Comments 
      Iterable<Comment> comments = commentRepository.findAll();
      model.addAttribute("comments", comments);
      model.addAttribute("comment", new Post());

      return new RedirectView("/posts/{postID}");
  }
}
