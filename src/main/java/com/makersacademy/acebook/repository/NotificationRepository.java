package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Notification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
    public List<Notification> findByUserIdAndSeenOrderByCreatedAtDesc(Long userId, boolean seen);
    public Long countByUserIdAndSeen(Long userId, boolean seen);
}
