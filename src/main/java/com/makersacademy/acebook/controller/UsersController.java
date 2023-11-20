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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Optional;

import static java.lang.Boolean.valueOf;

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
    public RedirectView signup(@ModelAttribute User user, RedirectAttributes attributes) {

        String usernameErrorMsg = "Please enter a username";
        String passwordErrorMsg = "Please enter a password";

        if (user.getUsername().trim().isEmpty() && user.getPassword().trim().isEmpty()){
            attributes.addAttribute("usernameError", usernameErrorMsg);
            attributes.addAttribute("passwordError", passwordErrorMsg);
            return new RedirectView("/users/new");
        } else if (user.getPassword().trim().isEmpty()) {
            attributes.addAttribute("passwordError", passwordErrorMsg);
            return new RedirectView("/users/new");
        } else if (user.getUsername().trim().isEmpty()) {
            attributes.addAttribute("usernameError", usernameErrorMsg);
            return new RedirectView("/users/new");
        }

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

        ModelAndView modelAndView = new ModelAndView("/users/show");
        modelAndView.addObject("currentUser", principalUser);
        modelAndView.addObject("user", user);

        assert principalUser != null;
        Iterable<Friend> userRequests = friendRepository.findAllByRequesterId(principalUser.getId());

        for (Friend friend : userRequests) {
            if (Objects.equals(friend.getReceiverId(), id)) {
                modelAndView.addObject("userRequest", friend);
            } else {
                modelAndView.addObject("userRequest", null);
            }

            System.out.println(id);
            System.out.println(friend.getReceiverId());
        }

        if (principal.getName().equals(Objects.requireNonNull(user).getUsername())) {
            modelAndView.addObject("isCurrentUser", true);
        } else {
            modelAndView.addObject("isCurrentUser", false);
        }

        return modelAndView;
    }
}
