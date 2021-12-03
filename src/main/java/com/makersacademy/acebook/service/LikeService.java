package com.makersacademy.acebook.service;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    @Autowired
    LikeRepository likeRepository;

    public List<Like> retrieveLikeFromDB(){
        List<Like> result = likeRepository.findAll();
        return result;
    }


}
