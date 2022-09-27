package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.Friendship;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

  List<User> findByid(Long id);

  // Bear in mind that any id value parameters must be of type "Long" (e.g. 1L)
  @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
  User findByUserName(String userName);

  @Query(value = "SELECT request_status FROM friends WHERE requester_id = ?1 AND requestee_id = ?2", nativeQuery = true)
  String getRequestStatus(Long requesterId, Long requesteeId);

  @Query(value = "SELECT friendship_id FROM friends WHERE (requester_id = ?1 AND requestee_id = ?2) OR (requestee_id = ?1 AND requester_id = ?2)", nativeQuery = true)
  Long getFriendshipId(Long userId1, Long userId2);

  @Query(value = "SELECT * FROM users WHERE NOT users.id IN (SELECT DISTINCT users.id FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE friends.requestee_id = ?1 OR friends.requester_id = ?1) AND users.id != ?1", nativeQuery = true)
  Iterable<User> getStrangers(Long userId);

  @Query(value = "SELECT users.* FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE (friends.requester_id = ?1 OR friends.requestee_id = ?1) AND users.id != ?1 AND friends.request_status = 'accepted'", nativeQuery = true)
  Iterable<User> getFriends(Long userId);

  @Query(value = "SELECT DISTINCT users.* FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE friends.requester_id IN (SELECT users.id FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE ((friends.requester_id = ?1 OR friends.requestee_id = ?1) AND users.id != ?1 AND friends.request_status = 'accepted') OR friends.requestee_id IN (SELECT users.id FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE (friends.requester_id = ?1 OR friends.requestee_id = ?1) AND users.id != ?1 AND friends.request_status = 'accepted')) AND users.id NOT IN (SELECT users.id FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE (friends.requester_id = ?1 OR friends.requestee_id = ?1) AND users.id != ?1 AND friends.request_status = 'accepted') AND users.id != ?1 AND friends.request_status = 'accepted' EXCEPT SELECT DISTINCT users.* FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE (friends.requester_id = ?1 OR friends.requestee_id = ?1) AND friends.request_status = 'pending' EXCEPT SELECT DISTINCT users.* FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE (friends.requester_id = ?1 OR friends.requestee_id = ?1) AND friends.request_status = 'blocked'", nativeQuery = true)
  Iterable<User> getFriendsOfFriends(Long userId);

  @Query(value = "SELECT DISTINCT users.* FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE (((friends.requester_id = ?1 AND friends.requestee_id IN (SELECT DISTINCT users.id FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE (friends.requester_id IN (?1, ?2) OR friends.requestee_id IN (?1, ?2)) AND users.id NOT IN (?1, ?2) AND friends.request_status = 'accepted')) OR (friends.requestee_id = ?1 AND friends.requester_id IN (SELECT DISTINCT users.id FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE (friends.requester_id IN (?1, ?2) OR friends.requestee_id IN (?1, ?2)) AND users.id NOT IN (?1, ?2) AND friends.request_status = 'accepted'))) AND users.id != ?1) INTERSECT SELECT DISTINCT users.* FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE (((friends.requester_id = ?2 AND friends.requestee_id IN (SELECT DISTINCT users.id FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE (friends.requester_id IN (?1, ?2) OR friends.requestee_id IN (?1, ?2)) AND users.id NOT IN (?1, ?2) AND friends.request_status = 'accepted')) OR (friends.requestee_id = ?2 AND friends.requester_id IN (SELECT DISTINCT users.id FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE (friends.requester_id IN (?1, ?2) OR friends.requestee_id IN (?1, ?2)) AND users.id NOT IN (?1, ?2) AND friends.request_status = 'accepted'))) AND users.id != ?2)", nativeQuery = true)
  Iterable<User> getMutualFriends(Long userId1, Long userId2);

  @Query(value = "SELECT users.* FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE (friends.requester_id = ?1 OR friends.requestee_id = ?1) AND users.id != ?1 AND friends.request_status = 'pending'", nativeQuery = true)
  Iterable<User> getFriendRequests(Long userId);

  @Query(value = "SELECT users.* FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE friends.requestee_id = ?1 AND users.id != ?1 AND friends.request_status = 'pending'", nativeQuery = true)
  Iterable<User> getIncomingFriendRequests(Long userId);

  @Query(value = "SELECT users.* FROM users JOIN friends ON friends.requester_id = users.id OR friends.requestee_id = users.id WHERE friends.requester_id = ?1 AND users.id != ?1 AND friends.request_status = 'pending'", nativeQuery = true)
  Iterable<User> getOutgoingFriendRequests(Long userId);

  @Query(value = "SELECT DISTINCT users.* FROM users JOIN friends ON friends.requestee_id = users.id WHERE friends.requester_id = ?1 AND friends.request_status = 'blocked'", nativeQuery = true)
  Iterable<User> getBlockedUsers(Long userId);

  // Can either use this or .save with a FriendshipRepository
  // @Modifying annotation is for queries that don't return a result set
  @Modifying
  @Query(value = "INSERT INTO friends (requester_id, requestee_id, request_status) VALUES (?1, ?2, 'pending')", nativeQuery = true)
  void addFriendRequest(Long requesterId, Long requesteeId);

  // For update/delete custom queries, need @Transactional annotation
  @Transactional
  @Modifying
  @Query(value = "UPDATE friends SET request_status = 'accepted' WHERE (requester_id = ?1 AND requestee_id = ?2) OR (requestee_id = ?1 AND requester_id = ?2) AND request_status = 'pending'", nativeQuery = true)
  void addAsFriends(Long requesterId, Long requesteeId);

  // For update/delete custom queries, need @Transactional annotation
  @Transactional
  @Modifying
  @Query(value = "UPDATE friends SET request_status = 'blocked' WHERE (requester_id = ?1 AND requestee_id = ?2) OR (requestee_id = ?1 AND requester_id = ?2)", nativeQuery = true)
  void blockUser(Long requesterId, Long requesteeId);

  // This can use to manually update request status
  @Transactional
  @Modifying
  @Query(value = "UPDATE friends SET request_status = ?3 WHERE (requester_id = ?1 AND requestee_id = ?2) OR (requestee_id = ?1 AND requester_id = ?2) AND request_status = 'pending'", nativeQuery = true)
  void updateStatus(Long requesterId, Long requesteeId, String newStatus);
}
