package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Like;
import org.springframework.data.repository.CrudRepository;

public interface LikesRepository extends CrudRepository<Like, Long> {

}
