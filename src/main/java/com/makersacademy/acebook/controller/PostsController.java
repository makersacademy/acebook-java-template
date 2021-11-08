package com.makersacademy.acebook.controller;

import java.util.List;

import com.makersacademy.acebook.lib.ImageUtil;
import com.makersacademy.acebook.lib.PostQuery;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

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
    @Autowired
    UserRepository Userrepository;
    
    @GetMapping("/posts")
    public String index(Model model) throws Exception{
        List<PostQuery> posts = repository.postsSortedByDate();
        Iterable<User> users = Userrepository.findAll();

        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("profileImage", users);

        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        repository.save(post);
        return new RedirectView("/posts");
    }
}
