package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LikeRepository likeRepository;

    private Long getUserId() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Long id = userRepository.findByUsername(authentication.getName()).getId();
        return id;
    }

    private String getUsername() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();
        return name;
    }

    private Timestamp getTimeStamp() {
        LocalDateTime time = LocalDateTime.now();
        System.out.println(time);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;
    }

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = repository.findAll(Sort.by(Sort.Direction.DESC, "id")); // filtering by id
        // User user = userRepository.findById(post.getUser_id()).get();\
        model.addAttribute("repo", repository);
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        // use the setter to store the user_id

        post.setUsername(this.getUsername());
        post.setDate(this.getTimeStamp());
        post.setUser_id(getUserId());
        repository.save(post);
        System.out.println(post.getTimeString());
        System.out.println(post.getDateString());
        // System.out.println(post.getDateString());
        Long nLikes = repository.findNumberOfLikesForAPost(post.getId());
        System.out.println(nLikes);

        // Long nComments = repository.findNumberOfCommentsForAPost(post.getId());
        // System.out.println(nLikes);
        // post.likes()
        return new RedirectView("/posts");
    }

    // @PostMapping("/posts/newLike")
    // @ResponseBody()
    // public RedirectView newLike(@RequestParam("postid") Long postId) {
    // Like newLike = new Like();
    // newLike.setUser_id(getUserId());
    // newLike.setPost_id(postId);
    // likeRepository.save(newLike);

    // return new RedirectView("/posts");
    // }

    @RequestMapping("/posts/like")
    @ResponseBody
    public RedirectView newLike(@RequestParam("postid") Long postid) {
        try {
            System.out.println(postid);
            System.out.println(getUserId());
            Like newLike = new Like();
            newLike.setUserid(getUserId());
            newLike.setPostid(postid);
            likeRepository.save(newLike);
        } catch (Exception e) {
            System.out.println("error");
            likeRepository.deleteByUseridAndPostid(getUserId(), postid);
        }
        return new RedirectView("/posts");
    }

}