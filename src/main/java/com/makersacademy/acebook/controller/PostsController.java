package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;


@Controller
public class PostsController {

    @Autowired
    PostRepository repository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = repository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, @AuthenticationPrincipal Principal principal) {
        post.setCreatedAt(new Date());
        User user = userRepository.findByUsername(principal.getName());
        post.setUser(user);
        repository.save(post);
        return new RedirectView("/posts");
    }

    @PostMapping("/posts/{id}/delete")
    public RedirectView delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        repository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Post deleted successfully.");
        return new RedirectView("/posts");
    }

//    Edit Method
//    @PostMapping("/posts/{id}/edit")
//    public RedirectView edit(@PathVariable Long id, @ModelAttribute Post post) {
//        //Using Optional to stop Null errors
//        Optional<Post> existingPost = repository.findById(id);
//        //checking if post exists then updating title and content
//        if (existingPost.isPresent()) {
//            Post updatedPost = existingPost.get();
//            updatedPost.setTitle(post.getTitle());
//            updatedPost.setContent(post.getContent());
//            //saving updated post to database
//            repository.save(updatedPost);
//        }
//        return new RedirectView("/posts");
//
//    }
@PostMapping("/posts/{id}/edit")
public RedirectView edit(@PathVariable Long id, @ModelAttribute Post post, BindingResult result, RedirectAttributes redirectAttributes) {
    Optional<Post> existingPost = repository.findById(id);
    if (existingPost.isPresent()) {
        Post updatedPost = existingPost.get();
        updatedPost.setTitle(post.getTitle());
        updatedPost.setContent(post.getContent());
        updatedPost.setCreatedAt(new Date()); // Update the date
        repository.save(updatedPost);
        redirectAttributes.addFlashAttribute("message", "Post updated successfully.");
    } else {
        redirectAttributes.addFlashAttribute("error", "Post not found.");
    }
    return new RedirectView("/posts");
}

    }
