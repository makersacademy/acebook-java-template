package com.makersacademy.acebook.controller;

import java.security.Principal;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LikeController {
    @Autowired
    LikeRepository likeRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/like")
    public RedirectView index() {
        return new RedirectView("/posts");
    }

    @PostMapping("/posts/{id}/likes")
    public RedirectView like(@PathVariable("id") Long id, Principal principal) {
        System.out.println("******* Liking a post");
        User user = userRepository.findByUsername(principal.getName());
        Like like = new Like(user.getId(), id);
        likeRepository.save(like);
        return new RedirectView("/posts");
    }

}