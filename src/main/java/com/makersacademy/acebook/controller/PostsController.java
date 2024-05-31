package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class PostsController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = postRepository.findAllByOrderByIdDesc();
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        model.addAttribute("comment", new Comment());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleName = authentication.getName();
        post.setUser_id(userRepository.findIdByUsername(currentPrincipleName));
        postRepository.save(post);
        return new RedirectView("/posts");
    }

    @PostMapping("/posts-like")
    public RedirectView like(@RequestParam("postId") Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        Post post = postOptional.get();
        post.incrementLikeCount();
        postRepository.save(post);
        return new RedirectView("/posts");
    }

    @PostMapping("/posts-comments")
    public RedirectView comment(@RequestParam("postId") Long postId, @RequestParam("content") String content) {

        Optional<Post> postOptional = postRepository.findById(postId);
        Post post = postOptional.get();
        Comment comment = new Comment();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleName = authentication.getName();
        comment.setContent(content);
        comment.setPost(post);
        comment.setUser_id(userRepository.findIdByUsername(currentPrincipleName));
        commentRepository.save(comment);
        return new RedirectView("/posts");
    }

}





