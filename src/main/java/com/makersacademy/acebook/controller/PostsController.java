package com.makersacademy.acebook.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import com.makersacademy.acebook.lib.ImageUtil;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

//import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/posts")
    public String index(Model model) throws Exception {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User thisUsers = userRepository.findByUsername(username).get(0);
        Iterable<Post> posts = repository.findAll(Sort.by(Sort.Direction.DESC, "time"));

        model.addAttribute("thisUser", thisUsers);
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, @RequestParam("file") MultipartFile file) throws IOException {
        post.contentimage = file.getBytes();

        repository.save(post);
        return new RedirectView("/posts");
    }

    @PostMapping("/deletePost/{id}")
    public RedirectView deletePost(@PathVariable Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User thisUser = userRepository.findByUsername(username).get(0);
        Post thisPost = repository.findById(id).get();
        if (thisPost.user.getId() == thisUser.getId()) {
            repository.deleteById(thisPost.getId());
        }
        return new RedirectView("/posts");
    }

    // public RedirectView create(@ModelAttribute Post post, Principal principal) {
    // post.setUserName(principal.getUsername());
    // repository.save(post);
    // return new RedirectView("/posts");
    // }
}
