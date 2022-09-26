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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class UsersController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "/signup/signup";
    }

    @PostMapping("/signup")
    public RedirectView signup(@ModelAttribute User user) {
        if (userRepository.findByUserName(user.getUsername()) != null) {
            return new RedirectView("/signup");
        } else {

            String password = user.getPassword();
            String encodedPassword = bCryptPasswordEncoder
                    .encode(password);
            user.setPassword(encodedPassword);
            userRepository.save(user);
            Authority authority = new Authority(user.getUsername(),
                    "ROLE_USER");
            authoritiesRepository.save(authority);
            userRepository.save(user);
            return new RedirectView("/login");
        }
    }
}
