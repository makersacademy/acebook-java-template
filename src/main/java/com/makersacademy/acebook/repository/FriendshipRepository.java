package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Friendship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

public interface FriendshipRepository extends CrudRepository<Friendship, Long> {
  @Query(
    value = "SELECT * FROM friends WHERE requester_id = ?1 OR requestee_id = ?1 AND request_status = 'pending'",
    nativeQuery = true)
  Iterable<Friendship> getPendingFriendships(Long userId);

  @Query(
    value = "INSERT INTO friends (requester_id, requestee_id, request_status) VALUES (?1, ?2, 'accepted'",
    nativeQuery = true)
  void addAsFriends(Long requesterId, Long requesteeId);
}
