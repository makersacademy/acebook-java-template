package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;
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
    //POST '/posts'
    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, Principal principal) {
      String regex = "\\s+";
      if(!post.getContent().isEmpty() && !post.getContent().matches(regex)){
        User user = userRepository.findByUsername(principal.getName());
        post.setUser(user);
        repository.save(post);}
      return new RedirectView("/posts");
    }

    @PostMapping("/posts/{id}/delete")
    public RedirectView delete(@PathVariable Long id){
        repository.deleteById(id);
        return new RedirectView("/posts");
    }

    @GetMapping("/posts/{id}/edit")
      public String edit(@PathVariable Long id, Model model) {
        Optional<Post> post = repository.findById(id);
        model.addAttribute("post", post.orElseThrow());
      return "posts/editPost";
    }

    @PostMapping("/posts/{id}/edit")
    public RedirectView update(@PathVariable("id") Long id, @ModelAttribute Post updatedPost){
      // Post post = repository.findById(id)
      // .orElseThrow(() -> new ResourceNotFoundException("Post not found :: "+ id ));
      
      Optional<Post> optionalPost = repository.findById(id);
      Post post = optionalPost.get();

      post.setContent(updatedPost.getContent());
      repository.save(post);

      return new RedirectView("/posts");
    }
}