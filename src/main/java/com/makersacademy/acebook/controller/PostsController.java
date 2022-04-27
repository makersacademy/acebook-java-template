package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
// import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;
    @Autowired
    UserRepository userRepository;

    private User getUser(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        return user;
    }

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = repository.findAllByOrderByTimestampDesc();
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, Principal principal) {
        post.setUser(getUser(principal));
        post.generateTimestamp();
        repository.save(post);
        return new RedirectView("/posts");
    }
}
