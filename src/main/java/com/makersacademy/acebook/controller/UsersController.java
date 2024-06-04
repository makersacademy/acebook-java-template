package com.makersacademy.acebook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class UsersController {

    private final UserRepository userRepository;
    private final AuthoritiesRepository authoritiesRepository;
    private final PostRepository postRepository;

    @Autowired
    public UsersController(UserRepository userRepository, AuthoritiesRepository authoritiesRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/users/new")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping("/users")
    public RedirectView signup(@ModelAttribute User user) {
        userRepository.save(user);
        Authority authority = new Authority(user.getUsername(), "ROLE_USER");
        authoritiesRepository.save(authority);
        return new RedirectView("/login");
    }

    @GetMapping("/users/profile")
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView("users/profile");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleName = authentication.getName();
        User user = userRepository.findByUsername(currentPrincipleName);
        modelAndView.addObject("user", user);

        List<Post> posts = postRepository.findByUserIdByOrderByIdDesc(userRepository.findIdByUsername(currentPrincipleName));
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("post", new Post());
        modelAndView.addObject("comment", new Comment());
        return modelAndView;
    }

    @PostMapping("/profile-pic-add")
    public RedirectView profilePicAdd(@RequestParam(value = "imageProfileInfoInput", required=false) String imageProfileInfo) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleName = authentication.getName();
        User user = userRepository.findByUsername(currentPrincipleName);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(imageProfileInfo, Map.class);
        String thumbnail_url = map.get("thumbnail_url");
        user.setProfilePicture(thumbnail_url);
        userRepository.save(user);
        return new RedirectView("/users/profile");
    }
}
