package com.makersacademy.acebook.service;

import com.makersacademy.acebook.model.Post;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    List<Post> findAllOrderByDateDesc();

    Optional<Post> findById(Long id);

    Post save(Post post);
}
