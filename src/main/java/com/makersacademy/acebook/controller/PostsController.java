package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.Reply;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.ReplyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;
    @Autowired
    ReplyRepository reply_repo;
    String testing_content ="Original";
    int testing_post_id;

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = repository.findAll();
        
        //int entries_length = (int) repository.count();
        //entries_length--;

        //reversing posts to get newest first
        List<Post> postsToList = new ArrayList<>();
        for(Post p: posts) {
            postsToList.add(p);
        }

        int sizeOfList = postsToList.size();
        List<Post> reversedPosts = new ArrayList<>();

        for (int i = 1; i<=sizeOfList;i++) {
            reversedPosts.add(postsToList.get(sizeOfList-i));
        }


        //Replies stuff here
        Iterable<Reply> replies = reply_repo.findAll();

        model.addAttribute("posts", reversedPosts);
        model.addAttribute("post", new Post());

        model.addAttribute("replies", replies);
        model.addAttribute("reply", new Reply());
        


        model.addAttribute("test_post_id", testing_post_id);
        model.addAttribute("test_content", testing_content);
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        Date date = new Date();
        post.setTime_posted(date);
        repository.save(post);
        return new RedirectView("/posts");
    }

    
}
