package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    PostRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    //comment method
    @PostMapping("/posts/{postId}/comments")
    public RedirectView createComment(@PathVariable Long postId, @ModelAttribute Comment comment, Model model) {
        Optional<Post> postOptional = repository.findById(postId);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            comment.setPost(post);
            commentRepository.save(comment);

            // Fetch all comments for the current post
            List<Comment> comments = commentRepository.findByPostId(postId);

            // Add the post and comments to the model
            model.addAttribute("post", post);
            model.addAttribute("comments", comments);

            // Redirect to the post page with comments section
            return new RedirectView("/posts/" + postId + "#comments", true, false);
        } else {
            // Handle the case when the post is not found
            return new RedirectView("/posts", true, false);
        }
    }
}
