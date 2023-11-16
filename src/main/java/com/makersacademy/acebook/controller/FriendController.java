package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class FriendController {

    @Autowired
    FriendRepository friendRepository;

    @PostMapping("/handleFriendRequest/{receiver_id}")
    public RedirectView create(@ModelAttribute Friend friend, Long receiver_id) {
        friendRepository.save(friend);
        RedirectView redirectView = new RedirectView("users/show");
        redirectView.addStaticAttribute("receiver_id", receiver_id);
        return redirectView;
    }
}
