package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.LikeRepository;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.*;


@Controller
public class PostsController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;
    CommentRepository commentRepository;

    @Autowired
    LikeRepository likeRepository;



//    add the current user's information to the model

    @GetMapping("/posts")
    public String index(Model model, @AuthenticationPrincipal Principal principal) {
        Iterable<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();



//        Iterable<Post> posts = repository.findAll();
        model.addAttribute("posts", posts);

        // Get current user information
        User currentUser = userRepository.findByUsername(principal.getName());
        model.addAttribute("currentUser", currentUser);

        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, @AuthenticationPrincipal Principal principal) {
        post.setCreatedAt(new Date());
        User user = userRepository.findByUsername(principal.getName());
        post.setUser(user);
        postRepository.save(post);
        return new RedirectView("/posts");
    }

    @PostMapping("/posts/{id}/delete")
    public RedirectView delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        postRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Post deleted successfully.");
        redirectAttributes.addFlashAttribute("flashPostId", id);

        return new RedirectView("/posts");
    }


    @PostMapping("/posts/{id}/edit")
    public RedirectView edit(@PathVariable Long id, @ModelAttribute Post post, BindingResult result, RedirectAttributes redirectAttributes) {
        Optional<Post> existingPostOptional = postRepository.findById(id);
        if (existingPostOptional.isPresent()) {
            Post existingPost = existingPostOptional.get();

            // Only update the title if a new value is provided
            if (post.getTitle() != null && !post.getTitle().isEmpty()) {
                existingPost.setTitle(post.getTitle());
            }

            // Only update the content if a new value is provided
            if (post.getContent() != null && !post.getContent().isEmpty()) {
                existingPost.setContent(post.getContent());
            }

            existingPost.setCreatedAt(new Date()); // Update the date
            postRepository.save(existingPost);
            redirectAttributes.addFlashAttribute("message", "Post updated successfully.");
            redirectAttributes.addFlashAttribute("flashPostId", id);

        } else {
            redirectAttributes.addFlashAttribute("error", "Post not found.");
        }
        return new RedirectView("/posts");
    }

    @PostMapping("/posts/{id}/like")
    public RedirectView like(@PathVariable Long id, @AuthenticationPrincipal Principal principal, RedirectAttributes redirectAttributes) {
        User currentUser = userRepository.findByUsername(principal.getName());
        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            Like existingLike = likeRepository.findByUserAndPost(currentUser, post);

            if (existingLike == null) {
                Like like = new Like();
                like.setUser(currentUser);
                like.setPost(post);
                likeRepository.save(like);
                redirectAttributes.addFlashAttribute("message", "Post liked.");
                redirectAttributes.addFlashAttribute("flashPostId", id);

            } else {
                likeRepository.delete(existingLike);
                redirectAttributes.addFlashAttribute("message", "Post unliked.");
                redirectAttributes.addFlashAttribute("flashPostId", id);

            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Post not found.");
        }

        return new RedirectView("/posts");
    }

    }
