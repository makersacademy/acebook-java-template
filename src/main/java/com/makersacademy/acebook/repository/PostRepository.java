package com.makersacademy.acebook.repository;

import java.util.List;

import com.makersacademy.acebook.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    @Query("FROM Post ORDER BY created_at DESC")
    // @Query("SELECT * FROM Post LEFT JOIN users ON(Post.fk_user_id=users.id)")
    List<Post> findAllOrderByDateDesc();

}
