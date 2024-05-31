package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Post;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    @Query(value = "SELECT c FROM Comment c JOIN c.post p WHERE p.id = :postId")
    Iterable<Comment> findCommentByPostId(@Param("postId") Long postId);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Comment c WHERE c.id = (SELECT MAX(c2.id) FROM Comment c2)")
    void deleteTestComment();
    public List<Post> findAllByOrderByIdDesc();
}