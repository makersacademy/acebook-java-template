package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.PostRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;
    Date tmpDate = null;
    String keyword = "";

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts;
        if(tmpDate == null && keyword.isEmpty()) posts = repository.findAll();
        else if(tmpDate == null && !keyword.isEmpty()) posts = repository.findByContentContaining(keyword);
        else if (tmpDate != null && keyword.isEmpty()) posts = repository.findByCreatedDate(tmpDate);
        else posts = repository.findAll(); // new method necessary to filter based on content and date
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        tmpDate = null;
        return "posts/index";

    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post,@RequestParam("search") String search, @RequestParam @DateTimeFormat(pattern="MM/dd/yyyy") String date){
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
