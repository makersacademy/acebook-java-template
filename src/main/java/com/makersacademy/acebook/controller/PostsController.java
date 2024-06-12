package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LikeRepository likeRepository;

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = repository.findAllByOrderByCreatedAtAsc();
        for (Post post: posts){
            post.setLikes(likeRepository.countByPost(post));
        }
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, Authentication auth) {
        post.setUser_id(userRepository.findByUsername(auth.getName()).getId());
//      Temporary while photo feature being developed
        post.setPhoto(null);
        repository.save(post);
        return new RedirectView("/posts");
    }
  
    @GetMapping("/posts/{post_id}/comments")
    public String viewComments(@PathVariable Long post_id, Model model) {
        Post post = repository.findById(post_id).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + post_id));
        Iterable<Comment> comments = commentRepository.findByPostIdOrderByCreatedAtAsc(post_id);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new Comment());
        return "comments/comments.html";
    }
    @PostMapping("posts/{post_id}/comments")
    public RedirectView createComment (@PathVariable Long post_id, @ModelAttribute Comment comment, Authentication auth) {
        comment.setUser_id(userRepository.findByUsername(auth.getName()).getId());
        comment.setPost_id(post_id);
        commentRepository.save(comment);
        return new RedirectView("/posts/" + post_id + "/comments");

    @PostMapping("/posts/like")
    public RedirectView likePost(@RequestParam Long postId, Authentication auth) {
        Optional<Post> optionalPost = repository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            User user = userRepository.findByUsername(auth.getName());
            List<Like> likes = likeRepository.findLikeByPostIdAndUserId(post.getId(), user.getId());
            if (likes.size() > 0) {
                for (Like like : likes) {
                    likeRepository.deleteById(like.getId());
                }
                return new RedirectView("/posts");
            }
            Like like = new Like(post, user);
            likeRepository.save(like);
            // Increment the like count in the post
//            repository.save(post);
            System.out.println("Hello world");
            System.out.println(likeRepository.countByPost(post));
        }
        return new RedirectView("/posts");
    }
}
