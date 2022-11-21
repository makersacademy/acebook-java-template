package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.Reply;
import com.makersacademy.acebook.repository.ReplyRepository;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class PostsController {

    @Autowired
    ReplyRepository reply_repo;
    @Autowired
    PostRepository prepository;
    @Autowired
    UserRepository urepository;
    @Autowired
    LikeRepository lrepository;

    @GetMapping("/posts")
    public String index(Model model, Principal principal) {
        // user id
        String userName = principal.getName();
        Optional<User> currentUser = urepository.findByUsername(userName);
        User user = currentUser.get();
        Long userIdLong = user.getId();

        Iterable<Post> posts = prepository.findAll();
        
        List<Post> postsToList = new ArrayList<>();

        // get all likes for each post
        HashMap<Long, Integer> allLikes = new HashMap<Long, Integer>();
        // save which ones the user has liked
        HashMap<Long, Boolean> postsUserHasLiked = new HashMap<Long, Boolean>();
        for(Post p: posts) {
            allLikes.put(
                p.getId(),
                lrepository.findAllByPost(p.getId()).size()
            );
            postsUserHasLiked.put(
                p.getId(),
                lrepository.hasLiked(p.getId(), userIdLong)
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


        //Replies stuff here
        Iterable<Reply> replies = reply_repo.findAll();

        model.addAttribute("posts", reversedPosts);
        model.addAttribute("post", new Post());
        model.addAttribute("replies", replies);
        model.addAttribute("reply", new Reply());
        model.addAttribute("like", new Like());
        model.addAttribute("allLikes", allLikes);
        model.addAttribute("postsUserHasLiked", postsUserHasLiked);
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, Principal principal) {
        Date date = new Date();
        String userName = principal.getName();
        Optional<User> currentUser = urepository.findByUsername(userName);
        User user = currentUser.get();
        Long userIdLong = user.getId();
        Integer userId = userIdLong.intValue();
        String image = user.getImage();
        post.setTime_posted(date);
        post.setUser_id(userId);
        post.setUsername(userName);
        post.setImage(image);
        prepository.save(post);
        return new RedirectView("/posts");
    }

    
}
