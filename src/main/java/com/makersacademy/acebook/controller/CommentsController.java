package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class CommentsController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @PostMapping("/posts/{postId}/comments")
    public RedirectView createComment(@PathVariable Long postId, @ModelAttribute Comment comment, Model model) {
        Optional<Post> postOptional = postRepository.findById(postId);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();

            // Retrieve the currently authenticated user
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user;
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                user = userRepository.findByUsername(username);
            } else {
                return new RedirectView("/login", true, false); // Redirect to login if user not found
            }

            comment.setPost(post);
            comment.setUser(user);
            commentRepository.save(comment);

            // Fetch all comments for the current post
            List<Comment> comments = commentRepository.findByPostId(postId);

            // Add the post and comments to the model
            model.addAttribute("post", post);
            model.addAttribute("comments", comments);

            // Redirect to the posts page to show updated comments
            return new RedirectView("/posts", true, false);
        } else {
            // Handle the case when the post is not found
            return new RedirectView("/posts", true, false);
        }
    }

    @PostMapping("/posts/{postId}/comments/{commentId}/delete")
    public RedirectView deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentRepository.deleteById(commentId);
        return new RedirectView("/posts", true, false);
    }

    @PostMapping("/posts/{postId}/comments/{commentId}/edit")
    public RedirectView editComment(@PathVariable Long postId, @PathVariable Long commentId, @ModelAttribute Comment comment) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);

        if (optionalComment.isPresent()) {
            Comment existingComment = optionalComment.get();
            existingComment.setContent(comment.getContent()); // Assuming content is the field to be edited
            commentRepository.save(existingComment);

            return new RedirectView("/posts", true, false);
        } else {
            // Handle the case when the comment is not found
            return new RedirectView("/posts", true, false);
        }
    }
}


