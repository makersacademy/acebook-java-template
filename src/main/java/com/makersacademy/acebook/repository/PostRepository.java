package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();
    List<Post> findByUserIdOrderByCreatedAtDesc(long user_id);
    List<Post> findByUserIdInOrderByCreatedAtDesc(List<Long> user_ids);
}
