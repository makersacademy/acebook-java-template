package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.*;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.FriendRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class FriendsController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;
    @Autowired
    FriendRepository friendRepository;

    @PostMapping("/friends/add")
    public RedirectView addFriend(@RequestParam String recipient_username, Authentication auth, @RequestParam String returnURL) {
        User sender = userRepository.findByUsername(auth.getName());
        User recipient = userRepository.findByUsername(recipient_username);

        //If no connection exists, we create a new friend entry in the database
        Friend newConnection = new Friend(sender, recipient);
        friendRepository.save(newConnection);
        return new RedirectView(returnURL);
    }

    @PostMapping("/friends/confirm")
    public RedirectView confirmFriend(@RequestParam String recipient_username, Authentication auth, @RequestParam String returnURL) {
        User sender = userRepository.findByUsername(auth.getName());
        User recipient = userRepository.findByUsername(recipient_username);

        Optional<Friend> existingConnection = friendRepository.findBySenderAndRecipient(recipient, sender);
        if (!existingConnection.isPresent()) return new RedirectView(returnURL);
        Friend connection = existingConnection.get();
        connection.setAccepted(true);
        friendRepository.save(connection);
        return new RedirectView(returnURL);
    }

    @PostMapping("/friends/delete")
    public RedirectView deleteFriend(@RequestParam String recipient_username, Authentication auth, @RequestParam String returnURL) {
        User sender = userRepository.findByUsername(auth.getName());
        User recipient = userRepository.findByUsername(recipient_username);

        //it doesn't matter which (or somehow both) of these are non-empty, we just want them deleted.
        Optional<Friend> sentConnection = friendRepository.findBySenderAndRecipient(sender, recipient);
        Optional<Friend> receivedConnection = friendRepository.findBySenderAndRecipient(recipient, sender);

        sentConnection.ifPresent(friend -> friendRepository.delete(friend));
        receivedConnection.ifPresent(friend -> friendRepository.delete(friend));

        return new RedirectView(returnURL);
    }

    @GetMapping("/friends")
    public String seeFriends(Model model, Authentication auth) {
        User user = userRepository.findByUsername(auth.getName());
        List<Friend> received = friendRepository.findAllByRecipientAndAccepted(user, true);
        List<Friend> sent = friendRepository.findAllBySenderAndAccepted(user, true);

        ArrayList<User> friends = new ArrayList<>();
        for (Friend connection: received) friends.add(connection.getSender());
        for (Friend connection: sent) friends.add(connection.getRecipient());


        model.addAttribute("friends", friends);
        return "friends/index";
    }
}