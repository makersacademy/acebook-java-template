package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends CrudRepository<Post, Long> {
    @Query("SELECT p FROM Post p ORDER BY p.createdAt DESC")
    Iterable<Post> findAllByOrderByCreatedAtDesc();
    @Query("SELECT COUNT(l) FROM Like l WHERE l.post = :post")
    long countLikesByPost(@Param("post") Post post);
}
