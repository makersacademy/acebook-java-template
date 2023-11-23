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
import java.util.LinkedHashMap;
import java.util.List;
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

//        get post to display at the top of page
        Optional<Post> post = postRepository.findById(id);
        Post currentPost = post.orElse(null);
        model.addAttribute("currentPost", currentPost);


        Optional<User> postUser = userRepository.findById(currentPost.getUserId());
        User postOwner = postUser.orElse(null);
        model.addAttribute("postOwner", postOwner);

        Optional<User> currentUser = userRepository.findByUsername(principal.getName());
        User principalUser = currentUser.orElse(null);


//        create new comment object to make a new comment using the form
        Comment commentObj = new Comment();
        model.addAttribute("newComment", commentObj);

//          Create a LinkedHashMap to store sorted data
        LinkedHashMap<Comment, User> commentsAndOwners = new LinkedHashMap<>();

//        get an ordered list of comments associated with the post we are visiting
        List<Comment> comments = commentRepository.findAllByPostIdOrderById(id);
        model.addAttribute("comments", comments);

//        for each comment, find the associated user/owner and add both to LinkedHaspMap
        for (Comment comment : comments) {
            Optional<User> commentOwner = userRepository.findById(comment.getUserId());
            User user = commentOwner.orElse(null);
            commentsAndOwners.put(comment, user);
        }
//        System.out.println(commentsAndOwners);
        model.addAttribute("commentsAndOwners", commentsAndOwners);
        model.addAttribute("currentUser", principalUser);
        model.addAttribute("profilePicture", principalUser.getImageUrl());
//        commentsAndOwners = { comment : owner , comment : owner , comment : owner , comment : owner }

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
