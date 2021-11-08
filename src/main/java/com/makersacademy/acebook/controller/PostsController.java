package com.makersacademy.acebook.controller;

import java.util.List;

import com.makersacademy.acebook.lib.PostQuery;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
//import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        Iterable<Post> posts = repository.findAll(Sort.by(Sort.Direction.DESC, "time"));
        // List<PostQuery> posts = repository.postsSortedByDate();
        // Iterable<Post> post = repository.findAll();

        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        repository.save(post);
        return new RedirectView("/posts");
    }

    @PostMapping("/deletePost/{id}")
    public RedirectView deletePost(@PathVariable Long id) {
        repository.deleteById(id);
        return new RedirectView("/posts");
    }
}
