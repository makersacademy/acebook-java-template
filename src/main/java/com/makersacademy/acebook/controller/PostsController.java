package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class PostsController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/posts")
    public String index(Model model, Principal principal) {
        Optional<User> currentUser = userRepository.findByUsername(principal.getName());
        User principalUser = currentUser.orElse(null);
        assert principalUser != null;

        Iterable<Post> posts = postRepository.findAllByOrderByTimestampDesc();
        ArrayList<HashMap<Post, User>> postsAndPosters = new ArrayList<>();

        for (Post post : posts) {
            HashMap<Post, User> hashMap = new HashMap<>();

            Optional<User> user = userRepository.findById(post.getUserId());
            User poster = user.orElse(null);

            hashMap.put(post, poster);
            postsAndPosters.add(hashMap);
        }

        System.out.println(postsAndPosters);

        model.addAttribute("postsAndPosters", postsAndPosters);
        model.addAttribute("newPost", new Post());
        model.addAttribute("profilePicture", principalUser.getImageUrl());

        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, Principal principal) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        post.setTimestamp(Timestamp.valueOf(timeStamp));
        Optional<User> currentUser = userRepository.findByUsername(principal.getName());
        User principalUser = currentUser.orElse(null);
        post.setUserId(principalUser.getId());
        postRepository.save(post);
        return new RedirectView("/posts");
    }

    @GetMapping("/post/{id}")
    public String show(@PathVariable Long id, Model model) {

        Optional<Post> post = postRepository.findById(id);
        Post currentPost = post.orElse(null);
        model.addAttribute("currentPost", currentPost);

        Comment newComment = new Comment();
        model.addAttribute("newComment", newComment);

        return "posts/show";
    }

    @PostMapping("/post/{id}")
    public ModelAndView createComment(@PathVariable Long id, @ModelAttribute Comment comment, Principal principal) {

//        to make a new comment for a post:
//        comment content, post_id, user_id
        comment.setPostId(id);

        Optional<User> currentUser = userRepository.findByUsername(principal.getName());
        User principalUser = currentUser.orElse(null);
        comment.setUserId(principalUser.getId());








        ModelAndView modelAndView = new ModelAndView("/post/{id}");
        modelAndView.addObject("comment", new Comment());
        return modelAndView;

    }
}
