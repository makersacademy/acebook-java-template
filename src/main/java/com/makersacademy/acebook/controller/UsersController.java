package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UsersController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;
    @Autowired
    PostRepository repository;

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
    
    @GetMapping("/profile")
    public String profile(Model model) {
      Iterable<Post> posts = repository.findAll();
      model.addAttribute("posts", posts);
      model.addAttribute("post", new Post());

        return "users/profile";

    }

    // @RequestMapping(method = RequestMethod.POST, value = "/images")
    // public RedirectView createFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
    //     imageService.createImage(file);

    //     return new RedirectView("/login");
    // }


    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }
}
