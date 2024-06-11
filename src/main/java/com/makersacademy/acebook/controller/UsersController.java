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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UsersController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/profile-pictures/";

    @GetMapping("/users/new")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping("/users")
    public RedirectView signup(@ModelAttribute User user, @RequestParam("profilePicture") MultipartFile file) {
        try{
            if(!file.isEmpty()){
                String filename = file.getOriginalFilename();
                Path path = Paths.get(UPLOAD_DIR + filename);
                Files.createDirectories(path.getParent());
                Files.write(path, file.getBytes());
                user.setProfilePictureUrl("/profile-pictures/" + UPLOAD_DIR + filename);
            }
            userRepository.save(user);
            Authority authority = new Authority(user.getUsername(), "ROLE_USER");
            authoritiesRepository.save(authority);
        } catch (IOException e){
            e.printStackTrace();
        }
        return new RedirectView("/login");
    }
}
