package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, UUID> {

    List<Like> findByPostID(UUID postID);

    @Query("SELECT COUNT(u) FROM Like u WHERE u.postID=:postID")
    long findLikeCount(@Param("postID") UUID postID);

    @Query("SELECT COUNT(u) FROM Like u WHERE u.postID=:postID")
    long like(@Param("postID") UUID postID);

    @Query("SELECT u FROM Like u WHERE u.postID=:postID AND u.username=:username")
    List<Like> exists(@Param("postID") UUID postID, @Param("username") String username);

    @Query("SELECT u FROM Like u WHERE u.postID=:postID AND u.username=:username")
    List<Like> likeDelete(@Param("postID") UUID postID, @Param("username") String username);
}
