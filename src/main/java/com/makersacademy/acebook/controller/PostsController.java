package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.PostRepository;
import org.omg.CORBA.Request;
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
    PostRepository postRepository;
    @Autowired
    LikeRepository likeRepository;

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = postRepository.findAllByOrderByTimestampDesc();

        for (Post post : posts) {
            Long likes = likeRepository.countByPostid(post.getId());
            post.setLikes(likes);
            postRepository.save(post);
        }
        // Long numberOfLikes = likeRepository.countByPostid(new Post().getId());
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        // model.addAttribute("numberOfLikes", numberOfLikes);
        model.addAttribute("like", new Like());
        // model.addAttribute("user", )
        return "posts/index";
    }

    // @GetMapping("/posts/{id}")
    // public String showNumberOfLikes(@PathVariable("id") long id, Model model) {
    //     Optional<Post> post = postRepository.findById(id);
    //     Long postid = post.get().getId();
    //     Long numberOfLikes = likeRepository.countByPostid(postid);
    //     model.addAttribute("numberOfLikes", numberOfLikes);
    //     return "posts/index";
    // }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        post.generateTimestamp();
        postRepository.save(post);
        return new RedirectView("/posts");
    }
}
