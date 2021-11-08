package com.makersacademy.acebook.controller;

import javax.validation.Valid;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.service.ICommentService;
import com.makersacademy.acebook.service.IPostService;
import com.makersacademy.acebook.service.PostService;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;

@Controller
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IPostService postService;

    // // @GetMapping("/posts/comments")
    // // public Iterable<Comment> getAllCommentsByPostId(@PathVariable(value =
    // "postId") Long postId, Pageable pageable) {
    // // return commentService.findByPostId(postId, pageable);
    // }

    // @PostMapping("/posts/comments")
    // public Comment createComment(@PathVariable(value = "postId") Long postId,
    // @Valid @RequestBody Comment comment) {
    // return commentService.findById(postId).map(post -> {
    // comment.setPost(post);
    // return commentService.save(comment);
    // });
    // }

    // ---This is the working code---
    @PostMapping("/posts/comments")
    public RedirectView create(@ModelAttribute Comment comment, @RequestParam(value = "post_id") Long postId,
            @RequestParam(value = "content") String content) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        comment.setUser(username);
        postService.findById(postId).map(post -> {
            comment.setPost(post);
            return comment;
        });
        comment.setContent(content);
        commentService.save(comment);
        return new RedirectView("/posts");
    }

    // @PostMapping("/posts/comments")
    // public RedirectView create(@ModelAttribute Comment comment) {
    // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    // String username = auth.getName();
    // comment.setContent(comment);
    // commentService.save(comment);
    // return new RedirectView("/posts");
    // }
}
