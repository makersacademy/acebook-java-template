package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.CommentRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;

    @Autowired
    LikeRepository likesRepository;

    @Autowired
    CommentRepository commentsRepository;

    Date tmpDate = null;

    String keyword = "";

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Like> likes = likesRepository.findAll();
        Iterable<Comment> comments = commentsRepository.findAll();
        Iterable<Post> posts;
        if(tmpDate == null && keyword.isEmpty()) posts = repository.findAll();
        else if(tmpDate == null && !keyword.isEmpty()) posts = repository.findByContentContaining(keyword);
        else if (tmpDate != null && keyword.isEmpty()) posts = repository.findByCreatedDate(tmpDate);
        else posts = repository.findByContentContainingAndCreatedDate(keyword, tmpDate); // new method necessary to filter based on content and date

        model.addAttribute("posts", posts);
        model.addAttribute("likes", likes);
        model.addAttribute("post", new Post());
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new Comment());
        tmpDate = null;
        return "posts/index";

    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post,@RequestParam("search") String search, @RequestParam @DateTimeFormat(pattern="MM/dd/yyyy") String date){
        Authentication loggedIn = SecurityContextHolder.getContext().getAuthentication();
        post.setUsername(loggedIn.getName());
        if(!post.getContent().isEmpty()) repository.save(post);
        keyword = search;
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");  
        try {
            tmpDate = formatter1.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new RedirectView("/posts");
     }
} 
