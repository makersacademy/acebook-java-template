package com.makersacademy.acebook.model;

import com.makersacademy.acebook.repository.LikesRepository;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

public class LikesHandler {
    private final PostRepository repository;
    private final LikesRepository likesRepository;
    private final CurrentUser currentUser;

    public LikesHandler(PostRepository repository, LikesRepository likesRepository, CurrentUser currentUser) {
        this.repository = repository;
        this.likesRepository = likesRepository;
        this.currentUser = currentUser;
    }

    public boolean liked(HttpServletRequest request, RedirectAttributes redirect) {
        String parameter = request.getParameter("postId");
        long postId = Long.parseLong(parameter);
        Optional<Post> query = repository.findById(postId);
        Post post = query.get();
        boolean isLikable = isLikable(redirect, post);
        if (isLikable) {
            post.incrementLikes();
            repository.save(post);
        }
        return isLikable;
    }

    private boolean isLikable(RedirectAttributes redirect, Post post) {
        boolean isSameUser = post.getUsername().equals(currentUser.getUsername());
        if (isSameUser) {
            redirect.addFlashAttribute("User cannot like its own posts");
        }
        boolean userHasAlreadyLikedPost = !postHasUser(post);
        if (userHasAlreadyLikedPost) {
            redirect.addFlashAttribute("User has already liked this posts");
        }
        return userHasAlreadyLikedPost && !isSameUser;
    }

    private Boolean postHasUser(Post post) {
        LikesList likeList = collectIdLikes(post);
        List<String> test = likeList.getList().stream().map(Like::getUsername).collect(Collectors.toList());
        return (test.contains(currentUser.getUsername()));
    }

    private LikesList collectIdLikes(Post post) {
        LikesList likeList = new LikesList();
        likeList.setList(likesRepository.findAllById(Collections.singleton(post.getId())));
        return likeList;
    }
}