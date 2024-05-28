package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PostRepository extends CrudRepository<Post, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Post p WHERE p.id = (SELECT MAX(p2.id) FROM Post p2)")
    void deleteTestPost();


}
