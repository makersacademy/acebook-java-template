package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.service.UploadService;
import org.flywaydb.core.internal.resource.classpath.ClassPathResource;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
public class UsersController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;
    @Autowired
    UploadService uploadService;

    @GetMapping("/users/new")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping("/users")
    public RedirectView signup(@ModelAttribute User user) {
        try {
            userRepository.save(user);
            Authority authority = new Authority(user.getUsername(), "ROLE_USER");
            authoritiesRepository.save(authority);
            return new RedirectView("/login");
        } catch (Exception e) {
            return new RedirectView("/users/new?exists");
        }
    }

    @GetMapping("login")
    public String getLoginView() {
        return "authentication/login";
    }

    @GetMapping("/users/{username}")
    public String user(@PathVariable String username, Model model) {
        User user = userRepository.findByUsername(username).get(0);
        model.addAttribute("user", user);
        return "/users/profile";
    }

    @PostMapping("/users/{username}/upload")
    public RedirectView upload(@PathVariable String username, MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        String path = String.format("%s", "acebook-images-javacadabra");
        String fileName = String.format("%s", file.getOriginalFilename());
        try {
            uploadService.upload(path, fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
        String filePath = String.format("https://%s.s3.eu-west-2.amazonaws.com/%s", path, fileName);
        User user = userRepository.findByUsername(username).get(0);
        user.setuserimage(filePath);
        userRepository.save(user);
        return new RedirectView("/users/{username}");

    }
}

//    // Return the image from the classpath location using HttpServletResponse
//    @GetMapping(value = "classpath1", produces = MediaType.IMAGE_JPEG_VALUE)
//    public void fromClasspathAsHttpServResp(HttpServletResponse response) throws IOException {
//
//        ClassPathResource imageFile = new ClassPathResource("");
//
//        StreamUtils.copy(imageFile.getInputStream(), response.getOutputStream());
//    }


//
//    @PostMapping("/photos/add")
//    public String addPhoto(@RequestPart("title") String title,
//                           @RequestPart("image") MultipartFile image, Model model)
//            throws IOException {
//        String id = photoService.addPhoto(title, image);
//        return "redirect:/photos/" + id;
//    }