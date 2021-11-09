package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.service.ICommentService;
import com.makersacademy.acebook.service.IPostService;

import org.springframework.beans.factory.annotation.Autowired;
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
    
}
