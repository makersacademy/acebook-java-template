package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Friendship;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FriendshipRepository extends CrudRepository<Friendship, Long> {
//    @Query("SELECT f FROM Friendship f WHERE f.status='pending' AND f.user_accepter=?1")
//    Iterable<Friendship> findRequestsForUser(Long userAccepterId);
}