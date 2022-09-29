package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.services.FriendsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

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

    @Autowired
    FriendsService friendsService;

    @Autowired
    CommentRepository comrepository;

    @GetMapping("/posts")
    public String index(Model model, HttpSession session) {
        // Get posts
        Iterable<Post> posts = postRepository.findAll();
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

        Iterable<Comment> comments = comrepository.findAll();
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new Comment());

        // Get friends service (for friends methods)
        model.addAttribute("friendsservice", friendsService);
        // Get user object
        model.addAttribute("user", new User());
        // Get non-blocked users (for search bar)
        model.addAttribute("allusers", userRepository.getNonBlockedUsers(ID));

        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUserName(username);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        post.setTimePosted(timestamp);
        post.setUser(user);
        postRepository.save(post);
        return new RedirectView("/posts");
    }
    /* id, title, content, time_posted, user_id */ 
}
