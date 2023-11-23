package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Optional;

@Controller
public class CommentController {


    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/post/{id}")
    public String show(@PathVariable Long id, Model model, Principal principal) {

        HashMap<Comment, User> commentsAndOwners = new HashMap<>();

//        get post
        Optional<Post> post = postRepository.findById(id);
        Post currentPost = post.orElse(null);
        model.addAttribute("currentPost", currentPost);

//        create new comment object
        Comment commentObj = new Comment();
        model.addAttribute("newComment", commentObj);

//        get list of comments for the post
        Iterable<Comment> comments = commentRepository.findAllByPostId(id);
        model.addAttribute("comments", comments);

        //profile image for user
        Optional<User> currentUser = userRepository.findByUsername(principal.getName());
        User principalUser = currentUser.orElse(null);
        assert principalUser != null;

        for (Comment comment : comments) {
            Optional<User> commentOwner = userRepository.findById(comment.getUserId());
            User user = commentOwner.orElse(null);
            commentsAndOwners.put(comment, user);
        }
        System.out.println(commentsAndOwners);
        model.addAttribute("commentsAndOwners", commentsAndOwners);
        model.addAttribute("profilePicture", principalUser.getImageUrl());



        return "posts/show";
    }

    @PostMapping("/post/addComment")
    public String createComment(@RequestParam String comment, @RequestParam Long postId, Principal principal) {

//        Get userID
        Optional<User> currentUser = userRepository.findByUsername(principal.getName());
        User principalUser = currentUser.orElse(null);

        Comment newComment = new Comment(comment, postId, principalUser.getId());

        commentRepository.save(newComment);

        return "redirect:/post/" + postId;

    }
}
