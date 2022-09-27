package com.makersacademy.acebook.controller;
import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDateTime;
import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class LikesController {

    @Autowired
    LikeRepository repository;

    @Autowired
    UserRepository userRepository;

    private Long getUserId() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Long id = userRepository.findByUsername(authentication.getName()).getId();
        return id;
    }

    
    private Timestamp getTimeStamp() {
        LocalDateTime time = LocalDateTime.now();
        System.out.println(time);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;
    }

    @GetMapping("/likes")
    public String like(Model model) {
        // Iterable<Like> likes = repository.findAll();
        // // User user = userRepository.findById(like.getUserId().get());
        // model.addAttribute("likes", likes);
        // model.addAttribute("like", new Like());
        return "likes/post";
    }

    @PostMapping("/likes/post")
    public RedirectView create(@RequestParam("postid") Long postid, @ModelAttribute Like like) {
        // use the setter to store the user_id
        // System.out.println(postid);

        like.setUserid(this.getUserId());
        // like.setPostid(this.getPostId());
        like.setDate(this.getTimeStamp());
        // System.out.println(this.getTimeStamp());
        // like.setUsername(this.getUsername());
        like.setPostid(postid);

        // comment.setDate(this.getDate());
        // comment.setUserid(getUserId());
        repository.save(like);
        // System.out.println(comment.getTimeString());
        // System.out.println(comment.getDateString());
        // Long nLikes = commentRrepository.findNumberOfLikesForAPost(post.getId());
        // System.out.println(nLikes);

        return new RedirectView("/likes/post?postid=" + postid);
    }

    @RequestMapping("/likes/post")
    public String likeByPost(@RequestParam("postid") Long postid, Model model) {
        List<Object[]> nlikes = repository.getUsersByPostid(postid);
        System.out.println(nlikes);
        try {
            List<Object[]> likes = repository.getUsersByPostid(postid);
            model.addAttribute("likes", likes);

        } catch (Exception e) {
            System.out.println("error");
        }

        return "likes/post";
    }
}

// @PostMapping("/likes")
// public RedirectView like(@ModelAttribute Like like) {
// like.setUser_id(getUserId());
// repository.save(like);

// return new RedirectView("/posts");
// }
