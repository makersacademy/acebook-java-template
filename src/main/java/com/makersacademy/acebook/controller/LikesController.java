package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Optional;

@Controller
public class LikesController {

    @Autowired
    UserRepository urepository;
    @Autowired
    LikeRepository lrepository;

    @PostMapping("/likes")
    public RedirectView create(@ModelAttribute Like likeModel, Principal principal) {
        String userName = principal.getName();
        Optional<User> currentUser = urepository.findByUsername(userName);
        User user = currentUser.get();
        Long userIdLong = user.getId();
        lrepository.toggleLike(likeModel.getPost_id(), userIdLong);
        return new RedirectView("/posts/" + likeModel.getPost_id());
    }
}
