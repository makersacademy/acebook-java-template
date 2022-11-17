package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public interface FriendRepository extends CrudRepository<Friend, Long> {

}
