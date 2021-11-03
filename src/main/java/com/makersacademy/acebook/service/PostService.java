package com.makersacademy.acebook.service;

import java.util.List;

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

}
