package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<Like, Long> {
  Long countByPostid(Long postid);
  Iterable<Like> findAllByUserid(Long userid);
}
