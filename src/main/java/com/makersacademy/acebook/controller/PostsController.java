package com.makersacademy.acebook.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.UUID;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

@Controller
public class PostsController {

    @Autowired
    private IPostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileStore fileStore;

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = postService.findAllOrderByDateDesc();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        model.addAttribute("user", user);
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, @RequestParam("image") MultipartFile file) {
        String path = String.format("%s/%s", "beta-aws-s3", UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());
        try {
            fileStore.upload(path, fileName, file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
        post.setImagePath(path);
        post.setImageFileName(fileName);
        if (post.getContent() != ""){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userService.findByUsername(username);
            post.setUsername(username);
            post.setUser(user);
            postService.save(post);
        }
        return new RedirectView("/posts");
    }

}
