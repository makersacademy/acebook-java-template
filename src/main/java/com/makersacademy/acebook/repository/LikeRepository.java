package com.makersacademy.acebook.repository;

import org.springframework.data.repository.CrudRepository;
import com.makersacademy.acebook.model.Like;

public interface LikeRepository extends CrudRepository<Like, Long> {

}
