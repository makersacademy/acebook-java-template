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
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/account")
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
            return "/account";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/account")
    public String updateAccount(@AuthenticationPrincipal Object principal, User updatedUser, RedirectAttributes redirectAttributes) {
        User user = null;

        if (principal instanceof UserDetails) {
            UserDetails currentUser = (UserDetails) principal;
            user = userService.findByUsername(currentUser.getUsername());

        } else if (principal instanceof OAuth2User) {
            OAuth2User oauthUser = (OAuth2User) principal;
            String email = oauthUser.getAttribute("email");
            user = userService.findByEmail(email);
        }

        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "User not authenticated.");
            return "redirect:/login";
        }

        // Update username if provided and different
        if (updatedUser.getUsername() != null && !updatedUser.getUsername().isEmpty() && !updatedUser.getUsername().equals(user.getUsername())) {
            if (userService.findByUsername(updatedUser.getUsername()) != null) {
                redirectAttributes.addFlashAttribute("error", "Username already exists. Please choose another one.");
                return "redirect:/account";
            }
            user.setUsername(updatedUser.getUsername());
        }

        // Update email if provided and different
        if (updatedUser.getEmail() != null && !updatedUser.getEmail().isEmpty() && !updatedUser.getEmail().equals(user.getEmail())) {
            if (userService.findByEmail(updatedUser.getEmail()) != null) {
                redirectAttributes.addFlashAttribute("error", "Email already exists. Please choose another one.");
                return "redirect:/account";
            }
            user.setEmail(updatedUser.getEmail());
        }

        // Update other fields
        user.setLanguage(updatedUser.getLanguage());
        user.setCity(updatedUser.getCity());

        try {
            userService.update(user);
            redirectAttributes.addFlashAttribute("message", "Account updated successfully!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/account";
    }

    @GetMapping("/account/password")
    public String passwordPage(@AuthenticationPrincipal Object principal, RedirectAttributes redirectAttributes, Model model) {
        User user = null;

        if (principal instanceof UserDetails) {
            UserDetails currentUser = (UserDetails) principal;
            user = userService.findByUsername(currentUser.getUsername());

        } else if (principal instanceof OAuth2User) {
            OAuth2User oauthUser = (OAuth2User) principal;
            String email = oauthUser.getAttribute("email");
            user = userService.findByEmail(email);
        }

        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "User not authenticated.");
            return "redirect:/login";
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "You cannot change your password because your account does not have a password set.");
            return "redirect:/account";
        }

        model.addAttribute("user", user);
        return "updatePassword";
    }

    @PostMapping("/account/password")
    public String changePassword(@AuthenticationPrincipal Object principal, String newPassword, String confirmPassword, RedirectAttributes redirectAttributes) {
        User user = null;

        if (principal instanceof UserDetails) {
            UserDetails currentUser = (UserDetails) principal;
            user = userService.findByUsername(currentUser.getUsername());

        } else if (principal instanceof OAuth2User) {
            OAuth2User oauthUser = (OAuth2User) principal;
            String email = oauthUser.getAttribute("email");
            user = userService.findByEmail(email);
        }

        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "User not authenticated.");
            return "redirect:/login";
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "You cannot change your password because your account does not have a password set.");
            return "redirect:/account";
        }

        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Passwords do not match.");
            return "redirect:/account/password";
        }

        userService.changePassword(user, newPassword);
        redirectAttributes.addFlashAttribute("message", "Password changed successfully!");
        return "redirect:/account";
    }

    @GetMapping("/account/login/oauth2/code/google")
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
        return "";
    }
}
