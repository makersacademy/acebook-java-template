package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CommentController {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/comments")
    public RedirectView create(@ModelAttribute Comment comment, Authentication auth) {
        String username = auth.getName() ;
        Long userId = userRepository.findByUsername(username).get(0).getId();
        comment.setUsersid(userId);
        commentRepository.save(comment);
        return new RedirectView("/posts");
    }
}
