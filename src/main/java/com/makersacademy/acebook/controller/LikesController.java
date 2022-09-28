package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

@Transactional
@Controller
public class LikesController {

    @Autowired
    LikeRepository repository;

    @PostMapping("/posts/like")
    public RedirectView create(String likedpost, String username) {

        
        if ( repository.findByLikedpostAndUsername(Long.parseLong(likedpost),username).isEmpty()) {

            Like like = new Like();
            like.setLikedpost(Long.parseLong(likedpost));
            like.setUsername(username);
            repository.save(like);
            return new RedirectView("/posts");
          } else {
            repository.deleteByLikedpostAndUsername(Long.parseLong(likedpost), username);
            return new RedirectView("/posts");
          }

    }

    @PostMapping("/wall/like")
    public RedirectView createlike(String likedpost, String username, String returnurl, HttpServletRequest request, Model model, HttpSession session) {

        
        if ( repository.findByLikedpostAndUsername(Long.parseLong(likedpost),username).isEmpty()) {

            Like like = new Like();
            like.setLikedpost(Long.parseLong(likedpost));
            like.setUsername(username);
            repository.save(like);
            return new RedirectView(returnurl);
          } else {
            repository.deleteByLikedpostAndUsername(Long.parseLong(likedpost), username);
            return new RedirectView(returnurl);
          }

    }
}
