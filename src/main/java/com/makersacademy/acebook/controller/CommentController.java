package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.dao.CommentDAO;
import com.makersacademy.acebook.dao.PostDAO;
import com.makersacademy.acebook.dao.UserDAO;
import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@RestController
public class CommentController {

  private final PostDAO postDAO;
  private final UserDAO userDAO;
  private final CommentDAO commentDAO;

  public CommentController(PostDAO postDAO, UserDAO userDAO, CommentDAO commentDAO) {
    this.postDAO = postDAO;
    this.userDAO = userDAO;
    this.commentDAO = commentDAO;
  }

  @GetMapping("/comments")
  Iterable<Comment> all() {
    return commentDAO.findAll();
  }

  @PostMapping("/comments")
  Comment newComment(@RequestBody Map<String,Object> body) {
    Comment newComment = new Comment();
    newComment.setContent(body.get("content").toString());
    Optional<User> author = userDAO.findById(Long.parseLong(body.get("user_id").toString()));
    Optional<Post> parentPost = postDAO.findById(Long.parseLong(body.get("post_id").toString()));
    newComment.setUser(author.get());
    newComment.setPost(parentPost.get());
    newComment.setCreated_at(Instant.now());
    return commentDAO.save(newComment);
  }

  @GetMapping("/comments/{post_id}")
  Iterable<Comment>commentsForPost(@PathVariable(value="post_id") Post post){
    return commentDAO.findByPost(post);
  }
}
