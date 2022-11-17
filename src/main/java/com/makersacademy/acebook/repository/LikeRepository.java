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

    public default Boolean hasLiked(Long postId, Long userId){
        Boolean hasLiked = false;
        Iterable<Like> allLikeRows = this.findAll();
        for(Like l: allLikeRows) {
            if(l.getUser_id() == userId && l.getPost_id() == postId){
                hasLiked = true;
            }
        }
        return hasLiked;
    }

    // toggle like
    public default void toggleLike(Long postId, Long userId) {
        if(postId!=null){
            // if row with these ids exists, remove
            // if not add
            Boolean hasLiked = false;
            Like cachedLike = new Like();
            Iterable<Like> allLikeRows = this.findAll();
            for(Like l: allLikeRows) {
                if(l.getUser_id() == userId && l.getPost_id() == postId){
                    cachedLike = l;
                    hasLiked = true;
                }
            }
            // adding or removing
            if(hasLiked){
                // remove
                this.delete(cachedLike);
            }else{
                // add
                cachedLike.setPost_id(postId);
                cachedLike.setUser_id(userId);
                this.save(cachedLike);
            }
        }
    }
}