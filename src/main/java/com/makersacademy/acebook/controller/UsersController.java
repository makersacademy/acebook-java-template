package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

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
    public RedirectView signup(@ModelAttribute User user) {
        userRepository.save(user);
        Authority authority = new Authority(user.getId(), "ROLE_USER");
        authoritiesRepository.save(authority);
        return new RedirectView("/login");
    }

//    @GetMapping("/users/my-profile")
//    public RedirectView sessionProfile(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String username = auth.getName();
//        System.out.println("\n!!!!!!!!!!!!!!!!!!!!!");
//        System.out.println(username);
//
////        model.addAttribute("username", auth.getPrincipal());
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!\n");
//        return new RedirectView("users/{username}");
//    }

        @GetMapping("/users/my-profile")
    public String sessionProfile(RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        redirectAttributes.addAttribute("username", username);
        System.out.println("\n!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(username);

//        model.addAttribute("username", auth.getPrincipal());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!\n");
        return "redirect:/users/{username}";
    }

    @GetMapping("/users/{username}")
    public String profile(@PathVariable("username") String username, Model model) {
        model.addAttribute("username", username);
        return "users/profile";
    }
}
