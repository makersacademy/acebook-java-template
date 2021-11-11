package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.lib.ImageUtil;
import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.List;

@Controller
public class UsersController {

    @Autowired
    PostRepository postRepository;
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
    public RedirectView signup(@ModelAttribute User user,  @RequestParam("file") MultipartFile file) throws IOException {
        user.profileimage = file.getBytes();
        userRepository.save(user);        

        Authority authority = new Authority(user.getUsername(), "ROLE_USER");
        authoritiesRepository.save(authority);
        return new RedirectView("/login");
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "users/login";
    }

    @GetMapping("/users/profile")
    public String myProfile(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User thisUser = userRepository.findByUsername(username).get(0);
        List<Post> myPosts = postRepository.findByUserId(thisUser.getId(), Sort.by(Sort.Direction.DESC, "time"));

        model.addAttribute("myPosts", myPosts);
        model.addAttribute("thisUser", thisUser);
        model.addAttribute("imgUtil", new ImageUtil());

        return "users/profile";
    }

}
