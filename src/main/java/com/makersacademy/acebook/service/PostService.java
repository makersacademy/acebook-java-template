package com.makersacademy.acebook.service;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostService {
    @Autowired
    PostRepository postRepository;


}
