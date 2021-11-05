package com.makersacademy.acebook.service;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService{

  @Autowired
  private PostRepository repository;

  @Override
  public List<Post> findAllByOrderByTimestampDesc() {
    return repository.findAllByOrderByTimestampDesc();
  }

  @Override
  public Post save(Post post) {
    return repository.save(post);
  }


}
