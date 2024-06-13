package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LikeRepository extends CrudRepository<Like, Long> {
    public Long countByPost (Post post);
    public List<Like> findLikeByPostIdAndUserId(long post_id, long user_id);

}
