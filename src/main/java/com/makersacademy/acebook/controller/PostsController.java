package com.makersacademy.acebook.controller;
import org.springframework.data.domain.Sort;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostService service;


    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        // Fetch comments for each post
        for (Post post : posts) {
            List<Comment> comments = commentRepository.findByPostId(post.getId());
            post.setComments(comments);
        }
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        model.addAttribute("comment", new Comment());
        return "posts/index";
    }

//    @PostMapping("/posts")
//    public RedirectView create(@ModelAttribute Post post) {
//        repository.save(post);
//        return new RedirectView("/posts");
//    }

    @PostMapping("/posts")
    public RedirectView createPost(@RequestParam("content") String content, @RequestParam("file") MultipartFile file){
        service.savePostToDB(file, content);
        return new RedirectView("/posts");
    }

    @PostMapping("/posts/like/{postId}")
    public RedirectView likePost(@PathVariable Long postId) {
        // Directly retrieve the post from the repository
        Post post = repository.findById(postId).orElse(null);


            // Increment likes count
        post.setLikes(post.getLikes() + 1);

            // Save the updated post
        repository.save(post);


        return new RedirectView("/posts");
    }
}
