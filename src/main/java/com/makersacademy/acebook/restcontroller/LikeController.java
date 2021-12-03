package com.makersacademy.acebook.restcontroller;

import com.amazonaws.services.apigateway.model.Model;
import com.amazonaws.services.codecommit.model.UserInfo;
import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/likeservice")
public class LikeController {
    @Autowired
    LikeService likeService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    PostRepository repository;

    @GetMapping("/getlikes")
    public List<Like> retrieveAllLikes(){
        List<Like> result=likeService.retrieveLikeFromDB();
        return result;
    }
//
//    @RequestMapping(value = "/like/{postID}", method = RequestMethod.POST)
//    public String likeapi(@Valid @PathVariable UUID postID, @ModelAttribute Like like,
//                          Model model)  {
//        // if any errors, re-render the user info edit form
//        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
//        String username = ((UserDetails)principal).getUsername();
//        User user = userRepository.findByUsername(username).get(0);
//        UUID id = user.getUserID();
//        //check whether like exists
//        try {
//            Like del = likeRepository.exists(postID, username).get(0);
//            likeRepository.deleteById(del.getLikeID());
//            Long variable = likeRepository.findLikeCount(postID);
//            Post post = repository.findById(postID).get();
//            post.setLikeCount(variable);
//            repository.save(post);
//            return "users/profile :: first";
//        } catch (Exception e) {
//            like.setPostID(postID);
//            like.setUserID(id);
//            like.setUsername(username);
//            likeRepository.save(like);
//            Long variable = likeRepository.findLikeCount(postID);
//            Post post = repository.findById(postID).get();
//            post.setLikeCount(variable);
//            repository.save(post);
//            return "users/profile :: second";
//        }
//    }
}
