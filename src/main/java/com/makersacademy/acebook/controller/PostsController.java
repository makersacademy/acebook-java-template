package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Controller
public class PostsController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = postRepository.findAllByOrderByTimestampDesc();
        model.addAttribute("posts", posts);
        model.addAttribute("newPost", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, Principal principal) {
        System.out.println("HEREEEEEEE");
        System.out.println("HEREEEEEEE");
        System.out.println(post);
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        post.setTimestamp(Timestamp.valueOf(timeStamp));
        Optional<User> currentUser = userRepository.findByUsername(principal.getName());
        User principalUser = currentUser.orElse(null);
        post.setUserId(principalUser.getId());
        postRepository.save(post);
        return new RedirectView("/posts");
    }

    @GetMapping("/post/{id}")
    public String show(@PathVariable Long id, Model model, Principal principal) {

        Optional<Post> post = postRepository.findById(id);
        Post currentPost = post.orElse(null);
        model.addAttribute("currentPost", currentPost);

        Optional<User> currentUser = userRepository.findByUsername(principal.getName());
        User principalUser = currentUser.orElse(null);
        model.addAttribute("userID", principalUser.getId());

        Comment commentObj = new Comment();
        commentObj.setComment("This is our test comment content");
        commentObj.setPostId(1L);
        commentObj.setUserId(2L);
        System.out.println(commentObj);
        model.addAttribute("newComment", commentObj);

        return "posts/show";
    }

    @PostMapping("/post/addComment")
    public RedirectView createComment(@ModelAttribute Comment newComment) {

        commentRepository.save(newComment);
        System.out.println("HEREEEEEEEEEEEEE");
        System.out.println("HEREEEEEEEEEEEEE");
        System.out.println("HEREEEEEEEEEEEEE");
        System.out.println("HEREEEEEEEEEEEEE");
        System.out.println(newComment);

//        set user_id:
//        Optional<User> currentUser = userRepository.findByUsername(principal.getName());
//        User principalUser = currentUser.orElse(null);
//        newComment.setUserId(principalUser.getId());

//        get visibility:
        System.out.println("PRINT OBJECT OUT AGAIN: ");
        commentRepository.save(newComment);

        return new RedirectView("/posts");

    }
}
