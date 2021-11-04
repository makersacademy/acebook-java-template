package com.makersacademy.acebook.controller;

import java.util.List;

import com.makersacademy.acebook.lib.PostQuery;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
//import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;
    
    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = repository.findAll();
        List<PostQuery> posts2 = repository.postsSortedByDate();

        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        model.addAttribute("posts2", posts2);
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        repository.save(post);
        return new RedirectView("/posts");
    }
}
