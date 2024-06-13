package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import java.util.List;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;

    @GetMapping("/posts")
    public String index(Model model) {
        List<Post> posts = (List<Post>) repository.findAll();
        List<Post> sortedPosts = posts.stream().sorted(Comparator.comparingLong(Post::getId) .reversed()) .collect(Collectors.toList());

        model.addAttribute("posts", sortedPosts);
        //model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        repository.save(post);
        return new RedirectView("/posts");
    }

    @PostMapping("/posts/{id}/likes")
    public RedirectView likePost(@PathVariable Long id) {
        Post post = repository.findById(id).get();
        post.setLikes(post.getLikes() + 1);
        repository.save(post);
        return new RedirectView("/posts");

    }
}

