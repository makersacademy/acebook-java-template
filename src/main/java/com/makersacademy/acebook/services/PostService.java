package com.makersacademy.acebook.services;

import com.makersacademy.acebook.dao.PostDAO;
import com.makersacademy.acebook.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostDAO postRepository;

    public Post addPost(Post post) {
        postRepository.save(post);
        return post;
    }

}
