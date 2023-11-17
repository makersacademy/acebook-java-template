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

    @PatchMapping("/users/friend")
    public String showForm(Model model) {
        model.addAttribute("friend", new Friend());
        return "form";
    }

    @PostMapping("/handleFriendRequest")
    public String create(@ModelAttribute Friend friend) {
        System.out.println(friend.getRequester_id());
        System.out.println(friend.getReceiver_id());
        return "success";
    }
}
