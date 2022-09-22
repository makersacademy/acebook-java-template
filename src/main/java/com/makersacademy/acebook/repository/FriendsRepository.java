package com.makersacademy.acebook.repository;

import org.springframework.data.repository.CrudRepository;
import com.makersacademy.acebook.model.Friend;

public interface FriendsRepository extends CrudRepository<Friend, Long> {

}
