package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Friend;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface FriendRepository extends CrudRepository<Friend, Long>  {
    List<Friend> findByStatus(String status);

    @Transactional
    @Modifying
    @Query("UPDATE Friend f SET f.status = :newValue WHERE f.requesterId = :requesterId")
    void updateStatusById(@Param("requesterId") Long id, @Param("newValue") String newValue);

    Iterable<Friend> findAllByRequesterId(Long requesterId);
}
