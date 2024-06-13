package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.*;
import com.makersacademy.acebook.repository.*;
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
import java.util.List;
import java.util.Optional;

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
    LikeRepository likeRepository;
    @Autowired
    LikeCommentRepository likeCommentRepository;

    @GetMapping("/posts")
    public String index(Model model) {

        Iterable<Post> posts = repository.findAllByOrderByCreatedAtDesc();
        for (Post post: posts){
            post.setLikes(likeRepository.countByPost(post));
        }
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, Authentication auth, @RequestParam("image") MultipartFile file) throws IOException {
        post.setUserId(userRepository.findByUsername(auth.getName()).getId());
        if (!file.isEmpty()) {
            StringBuilder fileNames = new StringBuilder("images/");
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
        comment.setUserId(userRepository.findByUsername(auth.getName()).getId());
        comment.setPostId(postId);
        commentRepository.save(comment);
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
        }
        return new RedirectView(returnURL);
    }
}
