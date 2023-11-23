package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    public List<Post> findAllByOrderByTimestampDesc();
}
