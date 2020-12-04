package com.makersacademy.acebook.dao;

import com.makersacademy.acebook.model.Post;

import org.springframework.data.repository.CrudRepository;

public interface PostDAO extends CrudRepository<Post, Long> {


}
