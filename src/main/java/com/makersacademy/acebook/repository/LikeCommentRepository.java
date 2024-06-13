package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.LikeComment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LikeCommentRepository extends CrudRepository <LikeComment, Long> {
    public Long countByComment (Comment comment);
    public List<LikeComment> findLikeCommentByCommentIdAndUserId(long comment_id, long user_id);
}
