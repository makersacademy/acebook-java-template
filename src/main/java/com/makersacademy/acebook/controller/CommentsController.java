package com.makersacademy.acebook.controller;

import java.lang.ProcessBuilder.Redirect;
import java.security.Principal;
import java.sql.Timestamp;
import java.time.Instant;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

  @PostMapping("/posts/{id}/comments")
  public RedirectView create(@PathVariable("id") Long id, Principal principal, @ModelAttribute Comment comment) {
    User user = userRepository.findByUsername(principal.getName());
    comment.setCreatedAt(Timestamp.from(Instant.now()));
    comment.setUserId(user.getId());
    comment.setPostId(id);
    commentRepository.save(comment);
    return new RedirectView("/posts");
  }
}



// public class PostsController {

//     @Autowired
//     PostRepository repository;

//     @GetMapping("/posts")
//     public String index(Model model) {
//         Iterable<Post> posts = repository.findAllByOrderByTimestampDesc();
//         model.addAttribute("posts", posts);
//         model.addAttribute("post", new Post() );
//         return "posts/index";
//     }

//     @PostMapping("/posts")
//     public RedirectView create(@ModelAttribute Post post) {
//         post.setTimestamp(post.createTimestamp());
//         repository.save(post);
//         return new RedirectView("/posts");
//     }
// }
