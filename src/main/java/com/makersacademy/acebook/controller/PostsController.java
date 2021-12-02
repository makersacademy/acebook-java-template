package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.*;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


@Controller
public class PostsController {

    @Autowired
    PostRepository repository;
    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/posts")
    public String index(Model model) {
        PostList postArrayList = new PostList();
        postArrayList.setList(repository.findAll());
        CommentList commentsList = new CommentList();
        commentsList.setList(commentRepository.findAll());
        model.addAttribute("commentList", commentsList.commentList);
        model.addAttribute("posts", postArrayList.postArrayList);
        model.addAttribute("post", new Post());
        model.addAttribute("comment", new Comment());
        model.addAttribute("showLogout", true);
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : principal.toString();
        post.populate(post.getContent(), LocalDateTime.now(), username, 0);
        repository.save(post);
        return new RedirectView("/posts");
    }

    @PostMapping("/posts/likes")
    public RedirectView likes(Model model, HttpServletRequest request) throws Exception {
        LikesHandler likesHandler = new LikesHandler(repository);
        likesHandler.handleLike(request);
        return new RedirectView("/posts");
    }

    @PostMapping("/posts/comment")
    public RedirectView comment(Post post, HttpServletRequest request){
        post = repository.findById(Long.parseLong(request.getParameter("commentsCondition"))).get();
        post.showOrHideComments();
        repository.save(post);
        return new RedirectView("/posts");
    }

    @PostMapping("/posts/comment/submit")
    public RedirectView commentSubmit(Model model, Post post, Comment comment, HttpServletRequest request){
        CommentHandler commentHandler = new CommentHandler();
        commentHandler.newComment(request, repository);
        Comment submitComment = new Comment(commentHandler.getId(),commentHandler.getComment(),commentHandler.getUsername());
        commentRepository.save(submitComment);
        return new RedirectView("/posts");
    }
}
