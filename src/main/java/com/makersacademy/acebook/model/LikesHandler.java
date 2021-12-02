package com.makersacademy.acebook.model;

import com.makersacademy.acebook.repository.PostRepository;

import javax.servlet.http.HttpServletRequest;

public class LikesHandler{
    private final PostRepository repository;

    public LikesHandler(PostRepository repository) {
        this.repository = repository;
    }

    public void handleLike(HttpServletRequest request) {
        Post post = repository.findById(Long.parseLong(request.getParameter("postId"))).get();
        post.incrementLikes();
        repository.save(post);
    }
}