package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.User;
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

    @GetMapping("/users/friend")
    public ModelAndView showFriends(Model model) {
        ModelAndView modelAndView = new ModelAndView("/users/friend");
        modelAndView.addObject("friend", new Friend());
        return modelAndView;
    }

    @PostMapping("/handleFriendRequest")
    public RedirectView create(@ModelAttribute Friend friend) {
        Friend newFriend = new Friend(friend.getRequester_id(), friend.getReceiver_id(), "PENDING");
        friendRepository.save(newFriend);
        return new RedirectView("/users/friend");
    }
}
