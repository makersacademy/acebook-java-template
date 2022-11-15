package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.ArrayList;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = repository.findAll();
        
        //int entries_length = (int) repository.count();
        //entries_length--;

        //reversing posts to get newest first
        List<Post> postsToList = new ArrayList<>();
        for(Post p: posts) {
            postsToList.add(p);
        }

        int sizeOfList = postsToList.size();
        List<Post> reversedPosts = new ArrayList<>();

        for (int i = 1; i<=sizeOfList;i++) {
            reversedPosts.add(postsToList.get(sizeOfList-i));
        }

        model.addAttribute("posts", reversedPosts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        repository.save(post);
        return new RedirectView("/posts");
    }
}
