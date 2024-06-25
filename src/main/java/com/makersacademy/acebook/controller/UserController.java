package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/account")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public String accountPage(@AuthenticationPrincipal Object principal, Model model) {
        User user = null;

        if (principal instanceof UserDetails) {
            UserDetails currentUser = (UserDetails) principal;
            user = userService.findByUsername(currentUser.getUsername());
        } else if (principal instanceof OAuth2User) {
            OAuth2User oauthUser = (OAuth2User) principal;
            String email = oauthUser.getAttribute("email");
            user = userService.findByEmail(email);
        }

        if (user != null) {
            model.addAttribute("user", user);
            return "account";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping
    public String updateAccount(@AuthenticationPrincipal UserDetails currentUser, User updatedUser, RedirectAttributes redirectAttributes) {
        User user = userService.findByUsername(currentUser.getUsername());
        user.setUsername(updatedUser.getUsername());
        if (!updatedUser.getPassword().isEmpty()) {
            user.setPassword(updatedUser.getPassword()); // The password will be encoded in the service
        }
        user.setEmail(updatedUser.getEmail());
        user.setLanguage(updatedUser.getLanguage());
        user.setCity(updatedUser.getCity());
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "Account updated successfully!");

        return "redirect:/account";
    }

    @GetMapping("/login/oauth2/code/google")
    public String handleGoogleLogin(OAuth2AuthenticationToken token, RedirectAttributes redirectAttributes) {
        OAuth2User oauthUser = token.getPrincipal();
        String email = oauthUser.getAttribute("email");
        User user = userService.findByEmail(email);

        if (user == null) {
            user = new User();
            user.setUsername(email);
            user.setEmail(email);
            user.setEnabled(true);
            userService.save(user);
        }

        redirectAttributes.addFlashAttribute("message", "Logged in with Google successfully!");
        return "redirect:/account";
    }
}

