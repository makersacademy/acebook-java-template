package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

@Controller
public class PostsController {

    @Autowired
    private IPostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    FileStore fileStore;

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = postService.findAllOrderByDateDesc();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);
        byte[] profilePhoto = userService.downloadProfilePhoto(user.getId());
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        model.addAttribute("profilePhoto", profilePhoto);
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);
        post.setUsername(username);
        post.setUser(user);
        postService.save(post);
        return new RedirectView("/posts");
    }

}
