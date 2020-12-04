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
public class PostController {

  private final PostDAO postDAO;
  private final UserDAO userDAO;
  private final CommentDAO commentDAO;

  public PostController(PostDAO postDAO, UserDAO userDAO, CommentDAO commentDAO) {
    this.postDAO = postDAO;
    this.userDAO = userDAO;
    this.commentDAO = commentDAO;
  }

  //get all posts
  @GetMapping("/posts")
  Iterable<Post> all() {
    return postDAO.findAll();
  }

  //creates new post with user_id column populated
  @PostMapping("/posts")
  Post newPost(@RequestBody Map<String,Object> body) {
    Post newPost = new Post();
    //add content to new post
    newPost.setContent(body.get("content").toString());
    //convert user_id from int to string then to long, and use long to find the correct user
    Optional<User> author = userDAO.findById(Long.parseLong(body.get("user_id").toString()));
    newPost.setCreated_at(Instant.now());
    //set that user as the user_id of the post, then save it to the database
    newPost.setUser(author.get());
    return postDAO.save(newPost);
  }

  @DeleteMapping("/posts/{post_id}")
  public void deletePost(@PathVariable(value="post_id") Post post) {
    Iterable<Comment> comments = commentDAO.findByPost(post);
    for(Comment comment : comments) {
      commentDAO.delete(comment);
    }
    postDAO.delete(post);
  }
}
