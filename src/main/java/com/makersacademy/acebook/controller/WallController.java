package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.services.FriendsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import java.security.Principal;
import java.util.Enumeration;

@Controller
public class WallController {
    @Autowired
    PostRepository postRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    LikeRepository likerepository;
    
    @Autowired
    FriendsService friendsService;

    
    @RequestMapping("/wall/**")
    public String wall(HttpServletRequest request, Model model, HttpSession session) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        // Get friends service
        model.addAttribute("friendsservice", friendsService);
        
        Long ID = userRepository.findByUserName(userName).getId();
        Enumeration<String> sessionAttributes = session.getAttributeNames();
        if (session.getAttribute("id") != ID) { session.setAttribute("id", ID); }
        model.addAttribute("id", session.getAttribute("id"));
        model.addAttribute("session", sessionAttributes);
        
        Iterable<Like> likes = likerepository.findAll();
        model.addAttribute("likes", likes);
        
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        
        // Get non-blocked users (for search bar)
        model.addAttribute("allusers", userRepository.getNonBlockedUsers(ID));

        return "/wall";
    }
    
}
