package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class LikesController {

    @Autowired
    LikeRepository repository;

    @Autowired
    UserRepository userRepository;

    private Long getUserId() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Long id = userRepository.findByUsername(authentication.getName()).getId();
        return id;
    }

    @GetMapping("/likes")
    public String like(Model model) {
        return "/likes";
    }

    @PostMapping("/likes")
    public RedirectView like(@ModelAttribute Like like) {
        like.setUser_id(getUserId());
        repository.save(like);

        return new RedirectView("/posts");
    }
}
