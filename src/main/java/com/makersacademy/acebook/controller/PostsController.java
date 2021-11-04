package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
// import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;

    @GetMapping("/posts")
    public String index(Model model) {
        // System.out.println(model);
        Iterable<Post> posts = repository.findAllByOrderByTimestampDesc();
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        // System.out.println(model);
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        System.out.println(post.getTimestamp());
        System.out.println("Hello world!");
        repository.save(post);
        return new RedirectView("/posts");
    }
}
