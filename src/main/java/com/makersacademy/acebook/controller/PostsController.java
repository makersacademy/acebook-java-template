package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/posts")
    public String posts(Model model) {
        Iterable<Post> posts = repository.findAll(Sort.by(Sort.Direction.DESC,"stamp"));
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        model.addAttribute("comment", new Comment());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        post.setStamp( LocalDateTime.now());
        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userRepository.findByUsername(username).get(0);
        UUID id = user.getUserID();
        post.setUserID(id);
        post.setUsername(username);
        repository.save(post);
        return new RedirectView("/posts");
    }

    @PostMapping("/posts/{postID}/comment")
    public RedirectView create(@PathVariable UUID postID, @ModelAttribute Comment comment) {
        commentRepository.save(comment);
        return new RedirectView("/posts");
    }
    @GetMapping("/posts/{postID}")
    public String post(@PathVariable UUID postID, Model model) {
        Post post = repository.findById(postID).get();
        model.addAttribute("post", post);
        return "/posts/post";
    }

}
