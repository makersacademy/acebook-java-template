package com.makersacademy.acebook.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

@Controller
public class CommentsController {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    private Long getUserId() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Long id = userRepository.findByUsername(authentication.getName()).getId();
        return id;
    }

    private String getUsername() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();
        return name;
    }

    private Long getPostId() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        // Long id = postRepository.findByUsername(authentication.getName()).getId();
        return 2L;
    }

    @GetMapping("/comments")
    public String comments(Model model) {
        Iterable<Comment> comments = commentRepository.findAll();
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new Comment());
        return "comments/post";
    }

    // @PostMapping("/comments")
    // public RedirectView create(@ModelAttribute Comment comment) {
    // commentRepository.save(comment);
    // return new RedirectView("/comments/index");
    // }

    @PostMapping("/comments/post")
    public RedirectView create(@RequestParam("postid") Long postid, @ModelAttribute Comment comment) {
        // use the setter to store the user_id
        // System.out.println(postid);

        comment.setUserid(this.getUserId());
        // comment.setPostid(this.getPostId());
        comment.setUsername(this.getUsername());
        comment.setPostid(postid);

        // comment.setDate(this.getDate());
        // comment.setUserid(getUserId());
        commentRepository.save(comment);
        // System.out.println(comment.getTimeString());
        // System.out.println(comment.getDateString());
        // Long nLikes = commentRrepository.findNumberOfLikesForAPost(post.getId());
        // System.out.println(nLikes);

        return new RedirectView("/comments/post?postid=" + postid);
    }

    @RequestMapping("/comments/post")
    // @ResponseBody
    public String commentByPost(@RequestParam("postid") Long postid, Model model) {
        // System.out.printf("THis is the post id for comments %d", postid);
        // System.out.println(postid);
        // List<Object[]> nComments = commentRepository.getUsersByPostid(postid);
        // System.out.println(nComments);
        try {
            List<Object[]> comments = commentRepository.getUsersByPostid(postid);
            model.addAttribute("comments", comments);
            model.addAttribute("postid", postid);
            model.addAttribute("comment", new Comment());

        } catch (Exception e) {
            System.out.println("error");
        }
        return "comments/post";
    }
}
