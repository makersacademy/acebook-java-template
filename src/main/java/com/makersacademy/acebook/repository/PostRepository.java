package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.Like;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

 @Query(value = "SELECT COUNT(1) FROM likes WHERE postid = ?1", nativeQuery = true)
 Long findNumberOfLikesForAPost(Long id);

 @Query(value = "SELECT COUNT(1) FROM comments WHERE post_id = ?1", nativeQuery = true)
 Long findNumberOfCommentsForAPost(Long id);

 // @Query(value = "SELECT COUNT(1) FROM comments WHERE post_id = ?1",
 // nativeQuery = true)
 // Boolean
}
