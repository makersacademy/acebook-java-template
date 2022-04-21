package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LikesController {

  @Autowired
  LikeRepository repository;

  @PostMapping("/likes")
  public RedirectView likePost(@ModelAttribute Like like) {
    repository.save(like);
    return new RedirectView("/posts");
  }

  
}
