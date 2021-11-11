package com.makersacademy.acebook.repository;

import java.util.List;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long> {

  // Integer countByPostId(Long post_id);

  // @Query("SELECT COUNT(post_id) FROM likes k WHERE l.post_id=:post_id")
  // long likesCount(@Param("post_id") Long post_id);
}