package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LikeRepository likeRepository;

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = repository.findAllByOrderByCreatedAtAsc();
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, Authentication auth) {
        post.setUser_id(userRepository.findByUsername(auth.getName()).getId());
//      Temporary while photo feature being developed
        post.setPhoto(null);
        repository.save(post);
        return new RedirectView("/posts");
    }

    @PostMapping("/posts/{postId}/like")
    public RedirectView likePost(@PathVariable Long postId, Authentication auth) {
        Optional<Post> optionalPost = repository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            User user = userRepository.findByUsername(auth.getName());
            Like like = new Like(post, user);
            likeRepository.save(like);
            // Increment the like count in the post
//            repository.save(post);
            System.out.println("Hello world");
            System.out.println(likeRepository.countByPost(post));
        }


        return new RedirectView("/posts");
    }
}
