package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class UsersController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;

    @GetMapping("/register")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping("/users")
    public RedirectView signup(@ModelAttribute User user) {
        userRepository.save(user);
        Authority authority = new Authority(user.getUsername(), "ROLE_USER");
        authoritiesRepository.save(authority);
        return new RedirectView("/login");
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        User currentUser = getCurrentUser();
        model.addAttribute("user", currentUser);
        return "users/profile";
    }

    @PostMapping("/profile")
    public RedirectView updateProfile(@ModelAttribute User user) {
        User currentUser = getCurrentUser();
        currentUser.setMobileNumber(user.getMobileNumber());
        currentUser.setEmailAddress(user.getEmailAddress());
        currentUser.setGender(user.getGender());
        currentUser.setCountry(user.getCountry());
        currentUser.setLanguage(user.getLanguage());
        userRepository.save(currentUser);
        return new RedirectView("/profile");
    }

    private User getCurrentUser() {
        // Assuming you're using Spring Security to manage user authentication
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userRepository.findByUsername(username);
    }
}
