package com.makersacademy.acebook.service;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Event;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.CommentsRepository;
import com.makersacademy.acebook.repository.EventRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    public void save(@ModelAttribute Comment comment, Long eventId, Authentication authentication) {
        User currentUser = userRepository.findByUsername(authentication.getName());
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            comment.setCreatedAt(new Date());
            comment.setUser(currentUser);
            comment.setEvent(event);
            commentsRepository.save(comment);
        }
    }

//    public void createComment (@ModelAttribute Comment comment, Authentication authentication) {
//        User currentUser = userRepository.findByUsername(authentication.getName());
//        comment.setCreatedAt(new Date());
//        comment.setUser(currentUser);
//        commentsRepository.save(comment);
//    }

//    public Comment createComment(String content, java.sql.Timestamp createdAt, Long userId, Long eventId) {
//        Comment comment = Comment.builder()
//                .content(content)
//                .createdAt(createdAt)
//                .userId(userId)
//                .eventId(eventId)
//                .build();
//
//        return Comment;
//    }
}