package com.makersacademy.acebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

@Controller
public class ProfilePageController {

    // @Autowired
    // private IProfileService profileService;

    @Autowired
    private IPostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("users/profile")
    public String index(Model model) {
        Iterable<Post> posts = postService.findAllOrderByDateDesc();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        model.addAttribute("user", user);
        return "users/profile";
    }

    @PostMapping("users/profile")
    public RedirectView create(@ModelAttribute Post post) {
        System.out.println(post.getContent());
        if (post.getContent() != "") {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userService.findByUsername(username);
            post.setUsername(username);
            post.setUser(user);
            postService.save(post);
        }
        return new RedirectView("users/profile");

    }

}
