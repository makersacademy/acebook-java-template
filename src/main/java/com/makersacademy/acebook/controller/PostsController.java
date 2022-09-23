package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
// import java.security.Principal,java.io.Serializable;

import java.util.List;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;

    @GetMapping("/posts")

    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
        System.out.println(auth.getPrincipal());
        System.out.println(auth.getCredentials());
        // System.out.println(auth.getDetails());
        Iterable<Post> posts = repository.findAll(Sort.by(Sort.Direction.DESC, "id")); // filtering by id
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        repository.save(post);
        return new RedirectView("/posts");
    }
}
