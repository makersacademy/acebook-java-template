package com.makersacademy.acebook.controller;

import com.google.common.collect.Lists;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.PostList;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;
//    ArrayList<Post> postArrayList = new ArrayList<>();

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = repository.findAll();
        PostList postArrayList = new PostList();
        postArrayList.setList(posts);
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        String username ;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            username = ((UserDetails)principal).getUsername();
        }
        else{
            username = principal.toString();
        }
        post.populate("content",LocalDateTime.now(),username,0);
        System.out.printf(username);
        repository.save(post);
        return new RedirectView("/posts");
    }
}
