package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.FriendRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class FriendController {

    @Autowired
    FriendRepository friendRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users/friend")
    public ModelAndView showFriends(Model model) {
        ModelAndView modelAndView = new ModelAndView("/users/friend");
        modelAndView.addObject("friend", new Friend());
        return modelAndView;
    }

    @GetMapping("/friends")
    public ModelAndView showRequests(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("/users/friends");

        Optional<User> currentUser = userRepository.findByUsername(principal.getName());
        User principalUser = currentUser.orElse(null);

        assert principalUser != null;
        List<Friend> requests = friendRepository.findByStatus("PENDING");
        ArrayList<Friend> userRequests = new ArrayList<>();

        for (Friend friend : requests) {
            if (Objects.equals(friend.getReceiver_id(), principalUser.getId())) {
                userRequests.add(friend);
            }
        }

        ArrayList<User> friendRequests = new ArrayList<>();

        for (Friend friend : userRequests) {
            Optional<User> optionalUser = userRepository.findById(friend.getRequester_id());
            User friendRequest = optionalUser.orElse(null);
            friendRequests.add(friendRequest);
        }

        modelAndView.addObject("friendRequests", friendRequests);
        System.out.println(friendRequests);
        return modelAndView;
    }

    @PostMapping("/handleFriendRequest")
    public RedirectView create(@ModelAttribute Friend friend) {
        Friend newFriend = new Friend(friend.getRequester_id(), friend.getReceiver_id(), "PENDING");
        friendRepository.save(newFriend);
        return new RedirectView("/users/friend");
    }
}
