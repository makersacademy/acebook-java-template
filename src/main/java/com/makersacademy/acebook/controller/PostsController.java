package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class PostsController {

    @Autowired
    PostRepository prepository;
    @Autowired
    LikeRepository lrepository;

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = prepository.findAll();
        
        //int entries_length = (int) repository.count();
        //entries_length--;
        List<Post> postsToList = new ArrayList<>();

        // get all likes for each post
        HashMap<Long, Integer> allLikes = new HashMap<Long, Integer>();
        for(Post p: posts) {
            allLikes.put(
                p.getId(),
                lrepository.findAllByPost(p.getId()).size()
            );
            
            postsToList.add(p);
        }
        System.out.println(allLikes);

        //reversing posts to get newest first
        int sizeOfList = postsToList.size();
        List<Post> reversedPosts = new ArrayList<>();
        for (int i = 1; i<=sizeOfList;i++) {
            reversedPosts.add(postsToList.get(sizeOfList-i));
        }

        model.addAttribute("posts", reversedPosts);
        model.addAttribute("post", new Post());
        model.addAttribute("allLikes", allLikes);
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        Date date = new Date();
        post.setTime_posted(date);
        prepository.save(post);
        return new RedirectView("/posts");
    }
}
