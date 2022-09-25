package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
  @Query(
    value = "SELECT * FROM users WHERE username = ?1",
    nativeQuery = true)
  User findByUserName(String userName);
  
  @Query(
    value = "SELECT request_status FROM friends WHERE (requester_id = ?1 AND requestee_id = ?2) OR (requester_id = ?2 AND requestee_id = ?1)",
    nativeQuery = true)
  String getRequestStatus(int requesterID, int requesteeID);
  
  @Query(
    value = "SELECT users.* FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE (friends.requester_id = '1' OR friends.requestee_id = '1') AND users.id != '1' AND friends.request_status = 'accepted'",
    nativeQuery = true)
  Iterable<User> getFriends(int userID);

  @Query(
    value = "SELECT users.* FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE (friends.requester_id = '1' OR friends.requestee_id = '1') AND users.id != '1' AND friends.request_status = 'pending'",
    nativeQuery = true)
  Iterable<User> getFriendRequests(int userID);
}