package com.makersacademy.acebook.controller;

import java.security.Principal;
import java.util.List;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

//import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/posts")
    public String index(Model model) {
        // Object principal =
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // String username = ((UserDetails) principal).getUsername();
        // User thisUser = userRepository.findByUsername(username).get(0);
        Iterable<Post> posts = repository.findAll(Sort.by(Sort.Direction.DESC, "time"));
        // List<PostQuery> posts = repository.postsSortedByDate();
        // Iterable<Post> post = repository.findAll();
        // model.addAttribute("thisUser", thisUser);
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        post.setUsername(username);
        repository.save(post);
        return new RedirectView("/posts");
    }

    @PostMapping("/deletePost/{id}")
    public RedirectView deletePost(@PathVariable Long id) {
        repository.deleteById(id);
        return new RedirectView("/posts");
    }

    // public RedirectView create(@ModelAttribute Post post, Principal principal) {
    // post.setUserName(principal.getUsername());
    // repository.save(post);
    // return new RedirectView("/posts");
    // }
}
