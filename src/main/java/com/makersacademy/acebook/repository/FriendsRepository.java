package com.makersacademy.acebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.makersacademy.acebook.model.Friend;

public interface FriendsRepository extends CrudRepository<Friend, Long> {
 @Query(value = "select exists (SELECT * FROM friends WHERE request_from = ?1 AND request_to = ?2 AND status_code = FALSE)", nativeQuery = true)
 boolean requestSent(Long user_from, Long user_to);
}
