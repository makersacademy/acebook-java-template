package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class CommentsController {

    @Autowired
    PostRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;


    //comment method
    @PostMapping("/posts/{postId}/comments")
    public RedirectView createComment(@PathVariable Long postId, @ModelAttribute Comment comment, Model model) {
        // Get the post by ID
        Optional<Post> post = repository.findById(postId);

        if (post.isPresent()) {
            // Set the post ID for the comment
            comment.setPost(post.get());

            // Save the comment to the database
            commentRepository.save(comment);

            // Add the post and comments to the model
            model.addAttribute("post", post.get());
            model.addAttribute("comments", commentRepository.findById(postId));

            // Redirect to the post page with a success message
            return new RedirectView("/posts/" + postId + "#comments", true, false);
        } else {
            // Handle the case when the post is not found
            return new RedirectView("/posts", true, false);
        }
    }

}
