package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        return "users/new"; // static html file
    }

    @PostMapping("/users")
    public RedirectView signup(@ModelAttribute User user) throws Exception {

        if (userRepository.existsByUsername(user.getUsername())) {
            // return ("error/wrong");
            return new RedirectView("users/new?retry");
        } else {
            userRepository.save(user);
            Authority authority = new Authority(user.getUsername(), "ROLE_USER");
            authoritiesRepository.save(authority);

            // String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            // user = userRepository.findByUsername(user.getUsername());
            // user.setImage(fileName);
            // userRepository.save(user);
            // String uploadDir = "src/main/resources/static/image/" + user.getId();
            // FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            return new RedirectView("/login");
        }
    }

    @GetMapping("/allUsers")
    public String allUsers(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users/all";
    }
}
