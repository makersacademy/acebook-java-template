package com.makersacademy.acebook.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.User;

@Transactional // the process of refunding an item
public interface LikeRepository extends CrudRepository<Like, Long> {

 void deleteByUseridAndPostid(Long userid, Long postid);

 // $1
 @Query(value = "SELECT users.username FROM users INNER JOIN likes ON users.id = likes.userid WHERE likes.postid = ?1", nativeQuery = true)
 List<Object[]> getUsersByPostid(Long postid);
}
