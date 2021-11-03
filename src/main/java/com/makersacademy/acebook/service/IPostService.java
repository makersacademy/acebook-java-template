package com.makersacademy.acebook.service;

import com.makersacademy.acebook.model.Post;

import java.util.List;

public interface IPostService {
    List<Post> findAllOrderByDateDesc();
}
