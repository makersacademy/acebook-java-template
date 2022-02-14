package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

// import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;


    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = repository.findAll();
        model.addAttribute("posts", this.reversedPosts(posts));
        model.addAttribute("post", new Post());
        model.addAttribute("comment", new Comment());
        model.addAttribute("comments", this.commentRepository.findAll());
        // model.addAttribute("userName", this.userRepository.findById(id));
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        repository.save(post);
        return new RedirectView("/posts");
    }

    private List<Post> reversedPosts(Iterable<Post> posts) {
        List<Post> reversedList = new ArrayList<>();
        for(Post p : posts) {
            reversedList.add(p); 
        }
        Collections.reverse(reversedList);
        return reversedList;
    }
}
