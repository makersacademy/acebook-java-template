package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PostsController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/posts")
    public String index(Model model, Principal principal) {
        String username = principal.getName();
        Long userid = userRepository.findIdByUsername(username);
        Iterable<Post> posts = postRepository.findAllByOrderByTimestampDesc();
        Iterable<Like> userLikes = likeRepository.findAllByUserid(userid);
        ArrayList<Long> userLikesPostids = new ArrayList<Long>();
        for (Like like : userLikes) {
            Long postid = like.getPostid();
            userLikesPostids.add(postid);
        }
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        model.addAttribute("userLikesPostids", userLikesPostids);
        model.addAttribute("like", new Like());
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
        post.setLikes(Long.valueOf(0));
        postRepository.save(post);
        return new RedirectView("/posts");
    }
}
