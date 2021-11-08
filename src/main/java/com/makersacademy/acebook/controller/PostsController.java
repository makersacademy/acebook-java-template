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
import org.springframework.data.domain.Sort;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;
    @Autowired
    UserRepository Userrepository;
    
    @GetMapping("/posts")
    public String index(Model model) throws Exception{
        Iterable<Post> posts = repository.findAll(Sort.by(Sort.Direction.DESC, "time"));
        
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        post.user = User.user;
        repository.save(post);
        return new RedirectView("/posts");
    }
}
