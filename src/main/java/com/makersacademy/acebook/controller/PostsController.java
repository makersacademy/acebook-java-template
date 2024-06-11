package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = repository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        repository.save(post);
        return new RedirectView("/posts");
    }
    //delete Method
    @PostMapping("/posts/{id}/delete")
    public RedirectView delete(@PathVariable Long id){
        repository.deleteById(id);
        return new RedirectView("/posts");
    }
    //Edit Method
    @PostMapping("/posts/{id}/edit")
    public RedirectView edit(@PathVariable Long id, @ModelAttribute Post post) {
        //Using Optional to stop Null errors
        Optional<Post> existingPost = repository.findById(id);
        //checking if post exists then updating title and content
        if (existingPost.isPresent()) {
            Post updatedPost = existingPost.get();
            updatedPost.setTitle(post.getTitle());
            updatedPost.setContent(post.getContent());
            //saving updated post to database
            repository.save(updatedPost);
        }
        return new RedirectView("/posts");

    }
}
