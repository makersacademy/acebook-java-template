package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LikeRepository extends CrudRepository<Like, Long> {

    List<Like> findByLikedpostAndUsername(Long likedpost, String username);

    List<Like> deleteByLikedpostAndUsername(Long likedpost, String username);
    
}
