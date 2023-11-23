package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.FriendRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        List<Friend> pendingRequests = friendRepository.findByStatusAndReceiverId(principalUser.getId(), "PENDING");
        List<Friend> acceptedRequests = friendRepository.findByStatusAndReceiverId(principalUser.getId(), "ACCEPTED");

        ArrayList<User> friendRequests = new ArrayList<>();
        for (Friend friend : pendingRequests) {
            Optional<User> optionalUser = userRepository.findById(friend.getRequesterId());
            User friendRequest = optionalUser.orElse(null);
            friendRequests.add(friendRequest);
        }

        ArrayList<User> friends = new ArrayList<>();
        for (Friend friend : acceptedRequests) {
            Optional<User> optionalUser = userRepository.findById(friend.getRequesterId());
            User acceptedRequest = optionalUser.orElse(null);
            friends.add(acceptedRequest);
        }

        modelAndView.addObject("friendRequests", friendRequests);
        modelAndView.addObject("friends", friends);
        modelAndView.addObject("currentUser", principalUser);
        modelAndView.addObject("profilePicture", principalUser.getImageUrl());
        return modelAndView;
    }

    @PostMapping("/handleFriendRequest")
    public RedirectView handleFriendRequest(@RequestParam("requester_id") Long requesterId,
                                            @RequestParam("receiver_id") Long receiverId, Principal principal) {

        Optional<User> currentUser = userRepository.findByUsername(principal.getName());
        User principalUser = currentUser.orElse(null);
        assert principalUser != null;

        Friend friendRequest = new Friend(requesterId, receiverId, "PENDING");
        friendRepository.save(friendRequest);

        return new RedirectView("/users/" + receiverId);
    }

    @PostMapping("/handleFriendConfirmation")
    public String handleConfirmation(@RequestParam("action") String action,
                                 @RequestParam("requesterId") Long requesterId, Principal principal) {
        Optional<User> currentUser = userRepository.findByUsername(principal.getName());
        User receiverId = currentUser.orElse(null);
        assert receiverId != null;

        if ("accept".equals(action)) {
            friendRepository.updateStatusByRequesterIdAndReceiverId(requesterId, receiverId.getId(), "ACCEPTED");
        } else if ("deny".equals(action)) {
            friendRepository.updateStatusByRequesterIdAndReceiverId(requesterId, receiverId.getId(), "DENIED");
        }

        return "redirect:/friends";
    }
}
