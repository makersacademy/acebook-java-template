package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.repository.CommentRepository; // Import the CommentRepository class
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

public class CommentsController {
    @Autowired
    private CommentRepository commentRepository; // Autowire the CommentRepository instance

    @PostMapping("/posts/{post_id}/comments")
    public RedirectView createComment(String postId, String commentText) {
        Comment comment = new Comment();
        comment.setText(commentText);
        commentRepository.save(comment); // Call the save method on the CommentRepository instance

        return new RedirectView("/posts/" + postId);
    }
}



