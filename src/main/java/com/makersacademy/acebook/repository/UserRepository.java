package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.Friendship;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  // Bear in mind that any id value parameters must be of type "Long" (e.g. 1L)
  @Query(
    value = "SELECT * FROM users WHERE username = ?1",
    nativeQuery = true)
  User findByUserName(String userName);
  
  @Query(
    // value = "SELECT request_status FROM friends WHERE (requester_id = ?1 AND requestee_id = ?2) OR (requester_id = ?2 AND requestee_id = ?1)",
    value = "SELECT request_status FROM friends WHERE requester_id = ?1 AND requestee_id = ?2",
    nativeQuery = true)
  String getRequestStatus(Long requesterId, Long requesteeId);
  
  @Query(
    value = "SELECT * FROM users WHERE NOT users.id IN (SELECT DISTINCT users.id FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE friends.requestee_id = ?1 OR friends.requester_id = ?1)",
    nativeQuery = true)
  Iterable<User> getStrangers(Long userId);
  
  @Query(
    value = "SELECT users.* FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE (friends.requester_id = ?1 OR friends.requestee_id = ?1) AND users.id != ?1 AND friends.request_status = 'accepted'",
    nativeQuery = true)
  Iterable<User> getFriends(Long userId);

  @Query(
    value = "SELECT users.* FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE (friends.requester_id = ?1 OR friends.requestee_id = ?1) AND users.id != ?1 AND friends.request_status = 'pending'",
    nativeQuery = true)
  Iterable<User> getFriendRequests(Long userId);

  @Query(
    value = "SELECT users.* FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE friends.requestee_id = ?1 AND users.id != ?1 AND friends.request_status = 'pending'",
    nativeQuery = true)
  Iterable<User> getIncomingFriendRequests(Long userId);

  @Query(
    value = "SELECT users.* FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE friends.requester_id = ?1 AND users.id != ?1 AND friends.request_status = 'pending'",
    nativeQuery = true)
  Iterable<User> getOutgoingFriendRequests(Long userId);

  @Query(
    value = "INSERT INTO friends (requester_id, requestee_id, request_status) VALUES (?1, ?2, 'pending'",
    nativeQuery = true)
  void addFriendRequest(Long requesterId, Long requesteeId);

  @Query(
    value = "INSERT INTO friends (requester_id, requestee_id, request_status) VALUES (?1, ?2, 'accepted'",
    nativeQuery = true)
  void addAsFriends(Long requesterId, Long requesteeId);
}