package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<Like, Long> {
    Long countByPost (Post post);
}
