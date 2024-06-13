package com.makersacademy.acebook.service;

import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private S3Service s3Service;

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User updateUserProfile(User user, MultipartFile profilePicture) throws IOException {
        if (!profilePicture.isEmpty()) {
            String profilePictureUrl = s3Service.uploadFile(profilePicture, "profile_pictures");
            user.setProfilePictureUrl(profilePictureUrl);
        }
        return userRepository.save(user);
    }
}