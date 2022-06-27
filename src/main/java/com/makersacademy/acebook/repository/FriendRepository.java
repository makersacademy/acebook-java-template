package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Friend;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface FriendRepository extends CrudRepository<Friend, Long> {

  List<Friend> findByUserIdAndAccepted(Long userId, Boolean accepted);
}
