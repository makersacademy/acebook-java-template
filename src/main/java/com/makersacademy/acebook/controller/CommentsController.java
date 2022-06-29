package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CommentsController {

    @Autowired
    CommentRepository repository;

    @PostMapping("/comments")
    public RedirectView create(@ModelAttribute Comment comment) {
        repository.save(comment);
        return new RedirectView("/posts");
    }

}
