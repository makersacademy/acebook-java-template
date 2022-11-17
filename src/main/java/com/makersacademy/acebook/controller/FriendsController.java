package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.repository.FriendRepository;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class FriendsController {

    @Autowired
    FriendRepository friendRepository;
    @Autowired
    UserRepository urepository;

    @PostMapping("/friends")
    public RedirectView sendFriendRequest(@ModelAttribute Friend friend, Principal principal) {
        // split sent string
        //String sentString = friend.getToUser();
        //String[] arrOfStr = sentString.split("+", 2);
        // things
        String userName = principal.getName();
        Optional<User> currentUser = urepository.findByUsername(userName);
        User user = currentUser.get();
        Long userIdLong = user.getId();
        Integer userId = userIdLong.intValue();
        friend.setFromUser(userId);
        friendRepository.save(friend);
        Optional<User> toUserOptional = urepository.findById(friend.getToUser());
        User toUser = toUserOptional.get();
        String toUserName = toUser.getUsername();
        return new RedirectView(String.format("/users/%s",toUserName));
    }

}
