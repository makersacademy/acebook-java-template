package com.makersacademy.acebook.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.makersacademy.acebook.model.Like;

@Transactional // the process of refunding an item
public interface LikeRepository extends CrudRepository<Like, Long> {

 void deleteByUseridAndPostid(Long userid, Long postid);

 // @Query(value = "SELECT FROM likes where userid=?1 RETURNING *", nativeQuery =
 // true)
 // void deleteLikeByUserAndPostId(Long userid, Long postid);
}
