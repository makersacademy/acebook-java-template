package com.makersacademy.acebook.controller;


import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

import ch.qos.logback.classic.pattern.SyslogStartConverter;
import net.bytebuddy.TypeCache.Sort;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.aspectj.weaver.Iterators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class PostsController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/posts")
    public String index(Model model) {//Sort.by(Sort.Direction.DESC, "colName"
        Iterable<Post> posts = postRepository.findAll();
        Iterable<User> users = userRepository.findAll();

        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = auth.getName();
        Long currentUserId = userRepository.findByUsername(currentUsername).get().getId();

        model.addAttribute("currentUserId", currentUserId);
        List<Post> listOfPost = new ArrayList<>();
    //    converting iterable into a list
        for (Post post : posts) { // code block to be executed
            listOfPost.add(post);
        }
        // getting the size of array
        int postsArraySize = listOfPost.size();
    //    reversing the array
        List<Post> reversedPost = new ArrayList<>();
        for (int i = postsArraySize -1 ; i >= 0; i--) {
           reversedPost.add(listOfPost.get(i));
        }
        System.out.println(reversedPost);
        model.addAttribute("posts", reversedPost);
        model.addAttribute("post", new Post());
        //model.addAttribute("comment", new Comment());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String show(@PathVariable Long id, Model model){
        Optional<Post> objPost = postRepository.findById(id);
        
        Iterable<Comment> comments = commentRepository.findAll();

        ArrayList<Comment> relatedComments = new ArrayList<>();

        Post post = objPost.get();
        model.addAttribute("post", post);

        for (Comment c: comments) {
            if (c.getPost_id() == id) {
                relatedComments.add(c);
            }
        }
        model.addAttribute("comments", relatedComments);
        model.addAttribute("comment", new Comment());
        // model.addAttribute("post", new Post());
        return "posts/show";
    }


    private RedirectView RedirectView(String string) {
        return null;
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, Principal principal) {
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // get the current user
        String name = principal.getName();
        Optional<User> currentUser = userRepository.findByUsername(name); 
        User userObj = currentUser.get(); 
        Long userId = userObj.getId();
        post.setUserId(userId);
        // System.out.println(userId);
        Date timeStamp = new Date();
        post.setDate(timeStamp);
        // get his/her id
        // create setter in model post to set user_id
        // get the date of today
        // create setter method in the model to set the timestamp
        // if the post content is empty

        if (post.content.isEmpty()){
            // return the /post route
            return new RedirectView("/posts");
        } else {
            // else
            postRepository.save(post);
            return new RedirectView("/posts");
        }
        
        // end 
    }
}
