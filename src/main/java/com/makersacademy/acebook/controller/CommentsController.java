package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.CommentRepository; // Import the CommentRepository class
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
@Controller
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository; // Autowire the CommentRepository instance

    @Autowired
    PostRepository postRepository;

    @PostMapping("/posts/{id}/comments")
    public RedirectView createComment(String postId, String commentText) {
        System.out.println("TEST HERE!");
        //TASK = Go through optional
        Post post = postRepository.findById(Long.parseLong(postId)).get();
        Comment comment = new Comment(post, commentText);
        comment.setText(commentText);

        commentRepository.save(comment); // Call the save method on the CommentRepository instance

        return new RedirectView("/posts");
    }



}



