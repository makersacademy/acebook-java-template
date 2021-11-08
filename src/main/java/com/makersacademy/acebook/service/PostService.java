package com.makersacademy.acebook.service;

import java.util.List;
import java.util.Optional;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository repository;

    @Override
    public List<Post> findAllOrderByDateDesc() {
        return repository.findAllOrderByDateDesc();
    }

    @Override
    public Post save(Post post) {
        return repository.save(post);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return repository.findById(id);
    }

}
