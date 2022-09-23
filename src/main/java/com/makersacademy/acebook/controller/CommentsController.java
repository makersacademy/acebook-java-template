package com.makersacademy.acebook.controller;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.repository.CommentRepository;

@Controller
public class CommentsController {
    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/comments")
    public String comments(Model model) {
        Iterable<Comment> comments = commentRepository.findAll();
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new Comment());
        return "comments";
    }

    @PostMapping("/comments")
    public RedirectView create(@ModelAttribute Comment comment) {
        commentRepository.save(comment);
        return new RedirectView("/comments");
    }
}
