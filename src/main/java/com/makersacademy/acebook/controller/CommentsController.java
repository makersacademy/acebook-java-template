package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

import ch.qos.logback.classic.pattern.SyslogStartConverter;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class CommentsController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    // @GetMapping("/posts")
    // public String index(Model model) {
    //     Iterable<Post> posts = commentRepository.findAll();
    //     model.addAttribute("posts", posts);
    //     model.addAttribute("post", new Post());
    //     return "posts/index";
    // }

    @GetMapping("/comments")
    public String index(Model model) {
        Iterable<Comment> comments = commentRepository.findAll();
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new Comment());
        return "comments/index";
    }

    @PostMapping("/comments")
    public String create(@ModelAttribute Comment comment, Principal principal) {

        // get the current user
        String name = principal.getName();
        Optional<User> currentUser = userRepository.findByUsername(name); 
        User userObj = currentUser.get(); 
        Long userId = userObj.getId();
        comment.setUserId(userId);

        System.out.println(comment);

        if (comment.content.isEmpty()){
            // return the /post route
            return ("posts/index");
        } else {
            // else
            commentRepository.save(comment);
            return "comments/index";
        }
        
        // end 
    }
}
