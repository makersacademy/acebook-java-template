package com.makersacademy.acebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.makersacademy.acebook.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

 @Query(value = "SELECT users.username, comments.content FROM users INNER JOIN comments ON users.id = comments.userid WHERE comments.postid = ?1", nativeQuery = true)
 List<Object[]> getUsersByPostid(Long postid);
}
