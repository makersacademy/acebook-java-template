package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PostsController {

    @Autowired
    private IPostService postService;

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = postService.findAllByOrderByTimestampDesc();
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post() );
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        post.setTimestamp(post.createTimestamp());
        postService.save(post);
        return new RedirectView("/posts");
    }
}
