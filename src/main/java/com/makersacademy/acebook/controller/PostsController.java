package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.CurrentUser;
import com.makersacademy.acebook.model.LikesHandler;
import com.makersacademy.acebook.model.*;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.LikesRepository;
import com.makersacademy.acebook.repository.PostRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;


@Controller
public class PostsController {

    @Autowired
    PostRepository repository;
    @Autowired
    LikesRepository likesRepository;
    @Autowired
    CommentRepository commentRepository;

    CurrentUser currentUser = new CurrentUser();
    LikesHandler likesHandler;

    @GetMapping("/posts")
    public String index(Model model) {
        currentUser.setUsername();
        likesHandler = new LikesHandler();
        PostList postArrayList = new PostList();
        postArrayList.setList(repository.findAll());
        CommentList commentsList = new CommentList();
        commentsList.setList(commentRepository.findAll());
        model.addAttribute("commentList", commentsList.commentList);
        model.addAttribute("posts", postArrayList.postArrayList);
        model.addAttribute("post", new Post());
        model.addAttribute("comment", new Comment());
        model.addAttribute("showLogout", true);
        model.addAttribute("like",new Like());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        post.populate(post.getContent(), LocalDateTime.now(), currentUser.getUsername(), 0);
        repository.save(post);
        return new RedirectView("/posts");
    }

    @PostMapping("/posts/likes")
    public RedirectView likes(HttpServletRequest request, RedirectAttributes redirect) throws Exception {
        Optional<Post> query = repository.findById(Long.parseLong(request.getParameter("postId")));
        Post post = query.get();
        LikesList test= new LikesList();
        test.setList(likesRepository.findAll());
        Iterable<Like> likesList = likesRepository.findAll();
        if (!likesHandler.liked(likesList,redirect, currentUser.getUsername(),post)) {
            redirect.addFlashAttribute("User is Unable to like this Post");
        } else {
            post.incrementLikes();
            repository.save(post);
            Like like = new Like(currentUser.getUsername(), post.getId());
            likesRepository.save(like);
        }
        return new RedirectView("/posts");
    }

    @PostMapping("/posts/comment")
    public RedirectView comment(Post post, @NotNull HttpServletRequest request) {
        post = repository.findById(Long.parseLong(request.getParameter("commentsCondition"))).get();
        post.showOrHideComments();
        repository.save(post);
        return new RedirectView("/posts");
    }

    @PostMapping("/posts/comment/submit")
    public RedirectView commentSubmit(Model model, Post post, Comment comment, HttpServletRequest request) {
        CommentHandler commentHandler = new CommentHandler();
        commentHandler.newComment(request, repository);
        Comment submitComment = new Comment(commentHandler.getId(), commentHandler.getComment(), commentHandler.getUsername());
        commentRepository.save(submitComment);
        return new RedirectView("/posts");
    }
}
