package com.makersacademy.acebook.model;

import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.data.repository.query.Parameter;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LikesHandler {
    private final PostRepository repository;

    public LikesHandler(PostRepository repository) {
        this.repository = repository;
    }

    public void handleLike(HttpServletRequest request) {
        String parameter = request.getParameter("postId");
        long postId = Long.parseLong(parameter);
        Optional<Post> query = repository.findById(postId);
        Post post = query.get();
        post.incrementLikes();
        repository.save(post);
    }
}