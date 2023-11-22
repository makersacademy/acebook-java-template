package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.FriendRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

@Controller
public class UsersController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;
    @Autowired
    FriendRepository friendRepository;

    @GetMapping("/users/new")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping("/users")
    public RedirectView signup(@ModelAttribute User user, @RequestParam("fileData") String fileData) {
        user.setImageUrl(fileData);

        userRepository.save(user);
        Authority authority = new Authority(user.getUsername(), "ROLE_USER");
        authoritiesRepository.save(authority);
        return new RedirectView("/login");
    }

    @GetMapping("/users/{id}")
    public ModelAndView show(@PathVariable Long id, Principal principal) {
        Optional<User> pageUser = userRepository.findById(id);
        User user = pageUser.orElse(null);

        Optional<User> currentUser = userRepository.findByUsername(principal.getName());
        User principalUser = currentUser.orElse(null);
        assert principalUser != null;

        ModelAndView modelAndView = new ModelAndView("/users/show");
        modelAndView.addObject("currentUser", principalUser);
        modelAndView.addObject("user", user);

        Iterable<Friend> userRequests = friendRepository.findByRequesterIdOrReceiverId(principalUser.getId(), principalUser.getId());

        for (Friend friend : userRequests) {
            if (Objects.equals(friend.getReceiverId(), id) || Objects.equals(friend.getRequesterId(), id)) {
                modelAndView.addObject("userRequest", friend);
                break;
            } else {
                modelAndView.addObject("userRequest", null);
            }
        }

        if (principal.getName().equals(Objects.requireNonNull(user).getUsername())) {
            modelAndView.addObject("isCurrentUser", true);
        } else {
            modelAndView.addObject("isCurrentUser", false);
        }

        return modelAndView;
    }
}
