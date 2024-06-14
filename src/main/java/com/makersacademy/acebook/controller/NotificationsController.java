package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.Utils;
import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.Notification;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.FriendRepository;
import com.makersacademy.acebook.repository.NotificationRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class NotificationsController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;
    @Autowired
    NotificationRepository notificationRepository;

    @GetMapping("/notifications")
    public String seeNotifications(Authentication auth, Model model) {
        User sessionUser = userRepository.findByUsername(auth.getName());
        List<Notification> newNotifications = notificationRepository.findByUserIdAndSeenOrderByCreatedAtDesc(sessionUser.getId(), false);
        List<Notification> recentNotifications = notificationRepository.findByUserIdAndSeenOrderByCreatedAtDesc(sessionUser.getId(), true);

        model.addAttribute("new_notifications", newNotifications);
        model.addAttribute("recent_notifications", recentNotifications);

        for (Notification notification : newNotifications){
            notification.setSeen(true);
        }
        notificationRepository.saveAll(newNotifications);

        return "notifications/index";
    }

}