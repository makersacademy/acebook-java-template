package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.CommentRepository;
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
            HashMap<Post, User> entry = new HashMap<>();

            Optional<User> optionalUser = userRepository.findById(post.getUserId());
            User poster = optionalUser.orElse(null);

            entry.put(post, poster);
            postsAndPosters.add(entry);
        }

//        System.out.println(postsAndPosters);

        model.addAttribute("postsAndPosters", postsAndPosters);
        model.addAttribute("newPost", new Post());
        model.addAttribute("profilePicture", principalUser.getImageUrl());

        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, Principal principal) {

//        get and set timestamp of post
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        post.setTimestamp(Timestamp.valueOf(timeStamp));

//        get user and set userid of post
        Optional<User> currentUser = userRepository.findByUsername(principal.getName());
        User principalUser = currentUser.orElse(null);
        post.setUserId(principalUser.getId());

//        save post
        postRepository.save(post);

        return new RedirectView("/posts");
    }

}
