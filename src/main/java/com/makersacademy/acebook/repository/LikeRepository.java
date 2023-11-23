package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Like;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface LikeRepository extends CrudRepository<Like, Long> {
    public List<Like> countByLikeStatusAndPostId(Boolean likeStatus, Long postId);
    public Optional<Like> findByUserIdAndPostId(Long userId, Long postId);
    @Transactional
    @Modifying
    @Query("UPDATE Like l SET l.likeStatus = CASE WHEN l.likeStatus = true THEN false ELSE true END WHERE l.userId = :userId AND l.postId = :postId")
    void updateLikeStatusByUserIdAndPostId(@Param("userId") Long userId,
                                                @Param("postId") Long postId);
}
