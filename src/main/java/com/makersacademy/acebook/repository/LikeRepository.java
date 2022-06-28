package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Like;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<Like, Long> {

  Long countByPostId(Long postId);

  List<Like> findByUserIdAndPostId(Long userId, Long postId);
}
