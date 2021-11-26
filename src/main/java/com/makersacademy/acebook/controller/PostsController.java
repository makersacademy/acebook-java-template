package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.PostList;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;
    PostList postArrayList = new PostList();

    @GetMapping("/posts")
    public String index(Model model) {
        postArrayList.setList(repository.findAll());
        model.addAttribute("posts", postArrayList.postArrayList);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        username = principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : principal.toString();
        post.populate(post.getContent(), LocalDateTime.now(), username, 0);
        System.out.printf(username);
        repository.save(post);
        return new RedirectView("/posts");
    }
}
