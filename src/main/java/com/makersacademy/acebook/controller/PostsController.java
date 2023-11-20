package com.makersacademy.acebook.controller;
import org.springframework.data.domain.Sort;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
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
        Iterable<Post> posts = repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        repository.save(post);
        return new RedirectView("/posts");
    }

    @PostMapping("/posts/like/{postId}")
    public RedirectView likePost(@PathVariable Long postId) {
        // Directly retrieve the post from the repository
        Post post = repository.findById(postId).orElse(null);


            // Increment likes count
        post.setLikes(post.getLikes() + 1);

            // Save the updated post
        repository.save(post);


        return new RedirectView("/posts");
    }
}
