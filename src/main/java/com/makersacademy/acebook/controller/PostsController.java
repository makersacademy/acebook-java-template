package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpSession;

import java.security.Principal;
import java.util.Enumeration;

@Controller
public class PostsController {

    @Autowired
    PostRepository postRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    LikeRepository likerepository;

    @GetMapping("/posts")
    public String index(Model model, HttpSession session) {
        // Get posts
        Iterable<Post> posts = repository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());

        // Get username
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        // Get user ID and store in session (have HttpSession as parameter and call session.getAttribute("id") to get)
        Long ID = userRepository.findByUserName(userName).getId();
        Enumeration<String> sessionAttributes = session.getAttributeNames();
        if (session.getAttribute("id") != ID) { session.setAttribute("id", ID); }
        model.addAttribute("id", session.getAttribute("id"));
        model.addAttribute("session", sessionAttributes);

        // Likes 
        Iterable<Like> likes = likerepository.findAll();
        model.addAttribute("likes", likes);
        model.addAttribute("like", new Post());

        return "posts/index";
    }


    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        post.setUser(user);
        postRepository.save(post);
        return new RedirectView("/posts");
    }
    /* id, title, content, time_posted, user_id */ 
}
