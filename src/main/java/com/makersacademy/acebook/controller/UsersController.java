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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Optional;

import static java.lang.Boolean.valueOf;

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
    public ModelAndView show(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElse(null);
        ModelAndView modelAndView = new ModelAndView("/users/show");
        modelAndView.addObject("user", user);
        System.out.println("This is an OBJECT!!!!!!");
        System.out.println(user);
        return modelAndView;
    }
}
