package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/users/new")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @GetMapping("/logon")
    public String signin(Model model) {
        return "/logon";
    }

    @GetMapping("/error")
    public String error(Model model) {
        return "/error";
    }

    @PostMapping("/users")
    public RedirectView signup(@ModelAttribute User user, Model model) {
        try {
            userRepository.save(user);
            Authority authority = new Authority(user.getUsername(), "ROLE_USER");
            authoritiesRepository.save(authority);
            return new RedirectView("/login");
        } catch (Exception e) {
            System.out.printf("HERE IS THE ERROR: %s", e.getLocalizedMessage());
            model.addAttribute("error_message", "Login failed");
            return new RedirectView("/error");
        }
    }
}
