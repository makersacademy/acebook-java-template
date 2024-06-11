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

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;

@Controller
public class UsersController {

    @Value("${file.upload-dir}")
    private String uploadDirectory;

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;

    @GetMapping("/users/new")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

//    @PostMapping("/users")
//    public RedirectView signup(@ModelAttribute User user) {
//        userRepository.save(user);
//        Authority authority = new Authority(user.getUsername(), "ROLE_USER");
//        authoritiesRepository.save(authority);
//        return new RedirectView("/login");
//    }
    @PostMapping("/users")
    public RedirectView signup(@ModelAttribute User user, @RequestParam("photo") MultipartFile file) {
        try {
            // Ensure the upload directory exists
            Path uploadPath = Paths.get(uploadDirectory);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Check if the file is not empty
            if (!file.isEmpty()) {
                // Save the file to the server
                byte[] bytes = file.getBytes();
                Path path = uploadPath.resolve(file.getOriginalFilename());
                Files.write(path, bytes);

                // Set the photo path to the user object
                user.setPhotoPath(path.toString());
            }

            // Save the user to the database
            userRepository.save(user);

            // Save the authority for the user
            Authority authority = new Authority(user.getUsername(), "ROLE_USER");
            authoritiesRepository.save(authority);

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception properly in a real application
        }

        return new RedirectView("/login");
    }
}
