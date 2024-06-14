package com.makersacademy.acebook.controller;
import com.makersacademy.acebook.model.Post;

import com.makersacademy.acebook.model.User;

import com.makersacademy.acebook.service.PostService;
import com.makersacademy.acebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @GetMapping
    public String getProfilePage(Authentication authentication, Model model) {
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);
        List<Post> posts = postService.getPostsByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        model.addAttribute("newPost", new Post());
        return "profile";
    }

    @PostMapping("/upload")
    public RedirectView uploadProfilePicture(@RequestParam("profilePicture") MultipartFile profilePicture, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);

        try {
            userService.updateUserProfile(user, profilePicture);
        } catch (IOException e) {
            e.printStackTrace();
            return new RedirectView("/profile?error");
        }

        return new RedirectView("/profile");
    }

    @PostMapping("/updateBio")
    public String updateBio(@RequestParam("bio") String bio, Authentication authentication, RedirectAttributes redirectAttributes){
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);
        userService.updateBio(user, bio);
        redirectAttributes.addFlashAttribute("message", "Bio updated successfully!");
        return "redirect:/profile";
    }
}