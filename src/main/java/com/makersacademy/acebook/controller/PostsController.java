package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.LongStream;
import java.util.stream.Stream;


@Controller
public class PostsController {

    @Autowired
    PostRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    LikeRepository likeRepository;

    @GetMapping("/posts")
    public String posts(Model model) {
        Iterable<Post> posts = repository.findAll(Sort.by(Sort.Direction.DESC,"stamp"));
        Iterable<Comment> comments = commentRepository.findAll();
        Iterable<Like> likes = likeRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new Comment());
        model.addAttribute("likes", likes);
        model.addAttribute("like", new Like());
        return "posts/index";
    }

    @GetMapping("/posts/{postID}")
    public String post(@PathVariable UUID postID, Model model) {
        Post post = repository.findById(postID).get();
        Iterable<Comment> comments = commentRepository.findByPostID(postID);
        List<Like> likes = likeRepository.findByPostID(postID);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new Comment());
        model.addAttribute("likes", likes);
        return "/posts/post";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        post.setStamp( LocalDateTime.now());
        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userRepository.findByUsername(username).get(0);
        UUID id = user.getUserID();
        post.setUserID(id);
        post.setUsername(username);
        repository.save(post);
        return new RedirectView("/posts");
    }

    @PostMapping("/posts/{postID}/comment")
    public RedirectView create(@PathVariable UUID postID, @ModelAttribute Comment comment) {
        comment.setStamp( LocalDateTime.now());
        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        comment.setUsername(username);
        User user = userRepository.findByUsername(username).get(0);
        UUID id = user.getUserID();
        comment.setUserID(id);
        commentRepository.save(comment);
        return new RedirectView("/posts/{postID}");
    }

    @PostMapping("/posts/{postID}/like")
    public RedirectView like(@PathVariable UUID postID, @ModelAttribute Like like) {
        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userRepository.findByUsername(username).get(0);
        UUID id = user.getUserID();
        //check whether like exists
        try {
            Like del = likeRepository.exists(postID, username).get(0);
            likeRepository.deleteById(del.getLikeID());
            Long variable = likeRepository.findLikeCount(postID);
            Post post = repository.findById(postID).get();
            post.setLikeCount(variable);
            repository.save(post);
            return new RedirectView("/posts/{postID}");
        } catch (Exception e) {
            like.setPostID(postID);
            like.setUserID(id);
            like.setUsername(username);
            likeRepository.save(like);
            Long variable = likeRepository.findLikeCount(postID);
            Post post = repository.findById(postID).get();
            post.setLikeCount(variable);
            repository.save(post);
            return new RedirectView("/posts/{postID}");
        }
    }

}
