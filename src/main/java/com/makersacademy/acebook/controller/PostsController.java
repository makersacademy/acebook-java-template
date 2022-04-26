package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

// import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class PostsController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/posts")
    public String index(Model model, Principal principal) {
        String username = principal.getName();
        Long userid = userRepository.findIdByUsername(username);
        Iterable<Post> posts = postRepository.findAllByOrderByTimestampDesc();
        Iterable<Comment> comments = commentRepository.findAllByOrderByTimestampAsc();
        Iterable<Like> userLikes = likeRepository.findAllByUserid(userid);
        ArrayList<Long> userLikesPostids = new ArrayList<Long>();
        for (Like like : userLikes) {
            Long postid = like.getPostid();
            userLikesPostids.add(postid);
        }
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        model.addAttribute("userLikes", userLikes);
        model.addAttribute("userLikesPostids", userLikesPostids);
        model.addAttribute("like", new Like());
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new Comment());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, Principal principal) {
        String username = principal.getName();
        Long userId = userRepository.findIdByUsername(username);
        post.addUserID(userId);
        post.generateTimestamp();
        post.setLikes(Long.valueOf(0));
        postRepository.save(post);
        return new RedirectView("/posts");
    }
}
