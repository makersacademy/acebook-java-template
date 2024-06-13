package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
public class PostsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = postService.getAllPostsFromNewestToOldest();
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(Post post, @RequestParam("image") MultipartFile image, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        post.setUser(user);

        try {
            postService.savePost(post, image);
        } catch (IOException e) {
            e.printStackTrace();
            return new RedirectView("/posts?error");
        }

        return new RedirectView("/posts");
    }
}