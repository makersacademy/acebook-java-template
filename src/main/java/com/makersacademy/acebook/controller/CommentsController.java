package com.makersacademy.acebook.controller;

import java.security.Principal;
import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

  @PostMapping("/posts/comments")
  public RedirectView create(String userid, String postid, String content) {
      /*
      String username = principal.getName();
      User user = userRepository.findByUserName(username);
      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
      comment.setTimePosted(timestamp);
      comment.setUser(user);
      comment.setPost(post);
      commentRepository.save(comment);
      */
      Comment commentobj = new Comment();
      commentobj.setContent(content);
      commentobj.setUserid(Long.parseLong(userid));
      commentobj.setPostid(Long.parseLong(postid));
      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
      commentobj.setTimePosted(timestamp);
      commentRepository.save(commentobj);

      return new RedirectView("/posts");
  }
}
