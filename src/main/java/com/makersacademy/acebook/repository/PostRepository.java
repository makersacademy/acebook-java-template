package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public interface PostRepository extends JpaRepository<Post, UUID> {

    ArrayList<Post> findAll();
    Post save(Post post);
    void deleteById(UUID postID);
}
