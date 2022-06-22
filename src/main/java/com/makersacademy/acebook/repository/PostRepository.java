package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Post;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByCreatedDate(Date createdDate);

}
