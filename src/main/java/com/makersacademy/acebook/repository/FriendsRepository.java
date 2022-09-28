package com.makersacademy.acebook.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.makersacademy.acebook.model.Friend;

public interface FriendsRepository extends CrudRepository<Friend, Long> {

 @Query(value = "SELECT friends.request_to FROM friends WHERE request_from = ?1", nativeQuery = true)
 Iterable<BigInteger> pendingFriends(Long request_from);

 // @Query(value = "SELECT users.username, likes.formatted_date,
 // likes.formatted_time FROM users INNER JOIN likes ON users.id = likes.userid
 // WHERE likes.postid = ?1", nativeQuery = true)

 @Query(value = "SELECT friends.id request_id, users.username FROM users INNER JOIN friends ON users.id = friends.request_from WHERE friends.request_to = ?1", nativeQuery = true)
 Iterable<Object[]> pendingFriendsTo(Long request_to);
}
