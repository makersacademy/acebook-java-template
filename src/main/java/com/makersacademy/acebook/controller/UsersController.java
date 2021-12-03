package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.*;
import com.makersacademy.acebook.repository.*;
import com.makersacademy.acebook.service.UploadService;
import org.flywaydb.core.internal.resource.classpath.ClassPathResource;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.*;

@Controller
public class UsersController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;
    @Autowired
    UploadService uploadService;
    @Autowired
    PostRepository repository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    LikeRepository likeRepository;

    @GetMapping("/users/new")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping("/users")
    public RedirectView signup(@ModelAttribute User user, MultipartFile file) {
        try {
            try {
                Map<String, String> metadata = new HashMap<>();
                metadata.put("Content-Type", file.getContentType());
                metadata.put("Content-Length", String.valueOf(file.getSize()));
                String path = String.format("%s", "acebook-images-javacadabra");
                String fileName = String.format("%s", file.getOriginalFilename());
                try {
                    uploadService.upload(path, fileName, Optional.of(metadata), file.getInputStream());
                } catch (IOException e) {
                    throw new IllegalStateException("Failed to upload file", e);
                }
                String filePath = String.format("https://%s.s3.eu-west-2.amazonaws.com/%s", path, fileName);
                user.setuserimage(filePath);
                userRepository.save(user);
                Authority authority = new Authority(user.getUsername(), "ROLE_USER");
                authoritiesRepository.save(authority);
                return new RedirectView("/login");
            } catch (Exception e) {
                userRepository.save(user);
                Authority authority = new Authority(user.getUsername(), "ROLE_USER");
                authoritiesRepository.save(authority);
                return new RedirectView("/login");
            }
        } catch (Exception e) {
            return new RedirectView("/users/new?exists");
        }

    }

    @GetMapping("login")
    public String getLoginView() {
        return "authentication/login";
    }

    @GetMapping("/users/{username}")
    public String user(@PathVariable String username, Model model) {
        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        String profilepic = ((UserDetails)principal).getUsername();
        User profile = userRepository.findByUsername(profilepic).get(0);
        User user = userRepository.findByUsername(username).get(0);
        Iterable<Post> posts = repository.findByUsername(username);
        Iterable<Comment> comments = commentRepository.findAll();
        Iterable<Like> likes = likeRepository.findAll();
        List<User> users = userRepository.findAll();
        model.addAttribute("profile", profile);
        model.addAttribute("users", users);
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new Comment());
        model.addAttribute("likes", likes);
        model.addAttribute("like", new Like());
        return "/users/profile";
    }

    @PostMapping("/users/{username}/upload")
    public RedirectView upload(@PathVariable String username, MultipartFile file) {
        if (file.isEmpty()) {
            return new RedirectView("/users/{username}?emptyfileerror");
        }
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        String path = String.format("%s", "acebook-images-javacadabra");
        String fileName = String.format("%s", file.getOriginalFilename());
        try {
            uploadService.upload(path, fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
        String filePath = String.format("https://%s.s3.eu-west-2.amazonaws.com/%s", path, fileName);
        User user = userRepository.findByUsername(username).get(0);
        user.setuserimage(filePath);
        userRepository.save(user);
        return new RedirectView("/users/{username}");


    }

}