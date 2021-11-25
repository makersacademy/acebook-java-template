package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.ErrorMessages;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.ResultSet;

@Controller
public class UsersController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;
    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/users/new")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping("/users")
    public RedirectView signup(@ModelAttribute User user) {
        try {
            if (user.getPassword().length() > 0) {
                user.setPassword(encoder.encode(user.getPassword()));
                userRepository.save(user);
                Authority authority = new Authority(user.getUsername(), "ROLE_USER");
                authoritiesRepository.save(authority);
                return new RedirectView("/login");
            } else {
                return new RedirectView("/users/new");
            }
        } catch (Exception e) {
            ErrorMessages error = new ErrorMessages();
            error.setErrorMessage();
            return new RedirectView("/users/new");
        }
    }
}