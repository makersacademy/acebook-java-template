package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;

    @Autowired
    LikeRepository likesRepository;

    String keyword = "";

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = repository.findAll();
        Iterable<Like> likes = likesRepository.findAll();
    if(keyword.isEmpty()) posts = repository.findAll();
    else posts = repository.findByContentContaining(keyword);
        model.addAttribute("posts", posts);
        model.addAttribute("likes", likes);
        model.addAttribute("post", new Post());
        return "posts/index";

    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, @RequestParam("search") String search) {
        System.out.println(keyword);
        keyword = search;
        repository.save(post);
        return new RedirectView("/posts");
    }

}
