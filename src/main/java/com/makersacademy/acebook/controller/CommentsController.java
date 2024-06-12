package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class CommentsController {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/posts/{id}/comments")
    public RedirectView create(Comment comment, @PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
        if (user.isPresent()) {
            comment.setUser(user.get());
        }
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            comment.setPost(post.get());
        }
        commentRepository.save(comment);
        return new RedirectView("/posts");
    }
}
