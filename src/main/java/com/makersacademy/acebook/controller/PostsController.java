package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.Utils;
import com.makersacademy.acebook.model.*;
import com.makersacademy.acebook.repository.*;
import org.slf4j.helpers.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
public class PostsController {

    public static final String UPLOAD_DIRECTORY = "src/main/resources/static/images";

    @Autowired
    PostRepository repository;

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    LikeCommentRepository likeCommentRepository;
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    NotificationRepository notificationRepository;

    @GetMapping("/posts")
    public String index(Model model, Authentication auth) {
        User sessionUser = userRepository.findByUsername(auth.getName());
        List<Long> idList = Utils.GetAllFriendsOfUser(sessionUser, true, friendRepository);

        Iterable<Post> posts = repository.findByUserIdInOrderByCreatedAtDesc(idList);
        for (Post post: posts){
            post.setLikes(likeRepository.countByPost(post));
        }
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, Authentication auth, @RequestParam("image") MultipartFile file) throws IOException {
        post.setUser(userRepository.findByUsername(auth.getName()));
        if (!file.isEmpty()) {
            StringBuilder fileNames = new StringBuilder("/images/");
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
            post.setPhoto(fileNames.toString());
        }
        else {
            post.setPhoto(null);
        }
        repository.save(post);
        return new RedirectView("/posts");
    }
  
    @GetMapping("/posts/{post_id}")
    public String viewComments(@PathVariable Long post_id, Model model) {
        Post post = repository.findById(post_id).orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + post_id));
        Iterable<Comment> comments = commentRepository.findByPostIdOrderByCreatedAtDesc(post_id);
        post.setLikes(likeRepository.countByPost(post));
        for (Comment comment : comments){
            comment.setLikes(likeCommentRepository.countByComment(comment));
        }
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new Comment());
        return "posts/postinfo.html";
    }
    @PostMapping("posts/comments")
    public RedirectView createComment (@RequestParam Long postId, @ModelAttribute Comment comment, Authentication auth, @RequestParam String returnURL) {
        comment.setUser(userRepository.findByUsername(auth.getName()));
        comment.setPost(postRepository.findById(postId).get());
        commentRepository.save(comment);
        Utils.CreateNotification(comment.getPost().getUser(), comment.getUser(), "post_comment", "/posts/" + postId, notificationRepository);
        return new RedirectView(returnURL);
    }

    @PostMapping("/posts/like")
    public RedirectView likePost(@RequestParam Long postId, Authentication auth, @RequestParam String returnURL) {
        Optional<Post> optionalPost = repository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            User user = userRepository.findByUsername(auth.getName());
            List<Like> likes = likeRepository.findLikeByPostIdAndUserId(post.getId(), user.getId());
            if (likes.size() > 0) {
                for (Like like : likes) {
                    likeRepository.deleteById(like.getId());
                }
                return new RedirectView(returnURL);
            }
            Like like = new Like(post, user);
            likeRepository.save(like);
            Utils.CreateNotification(post.getUser(), user, "post_like", "/posts/" + postId, notificationRepository);
        }
        return new RedirectView(returnURL);
    }

    @PostMapping("/posts/{post_id}/like")
    public RedirectView likeComment(@RequestParam Long commentId, Authentication auth, @RequestParam String returnURL) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            User user = userRepository.findByUsername(auth.getName());
            List<LikeComment> likes = likeCommentRepository.findLikeCommentByCommentIdAndUserId(comment.getId(), user.getId());
            if (likes.size() > 0) {
                for (LikeComment like : likes) {
                    likeCommentRepository.deleteById(like.getId());
                }
                return new RedirectView(returnURL);
            }
            LikeComment like = new LikeComment(comment, user);
            likeCommentRepository.save(like);
            Utils.CreateNotification(comment.getUser(), user, "comment_like", "/posts/" + comment.getPost().getId(), notificationRepository);
        }
        return new RedirectView(returnURL);
    }
}
