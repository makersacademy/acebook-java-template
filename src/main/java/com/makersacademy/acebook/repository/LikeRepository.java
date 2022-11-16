package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.ArrayList;

public interface LikeRepository extends CrudRepository<Like, Long> {

    // finds all likes by post ID
    public default List<Like> findAllByPost(Long postId){
        // get all rows from the likes table
        // using findAll
        List<Like> resultList = new ArrayList<>();

        Iterable<Like> allLikeRows = this.findAll();
        for(Like l: allLikeRows) {
            if(l.getPost_id() == postId){
                resultList.add(l);
            }
        }

        return resultList;
    }

}