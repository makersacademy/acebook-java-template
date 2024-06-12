package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
    @Query("SELECT p FROM Post p ORDER BY p.createdAt DESC")
    Iterable<Post> findAllByOrderByCreatedAtDesc();

}
