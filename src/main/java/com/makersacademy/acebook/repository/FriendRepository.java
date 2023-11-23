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
    @Query("SELECT f FROM Friend f WHERE f.receiverId = :receiverId AND f.status = :status")
    List<Friend> findByStatusAndReceiverId(@Param("receiverId") Long receiverId, @Param("status") String status);

    @Query("SELECT f FROM Friend f WHERE f.requesterId = :requesterId OR f.receiverId = :receiverId")
    List<Friend> findByRequesterIdOrReceiverId(@Param("requesterId") Long requesterId, @Param("receiverId") Long receiverId);

    @Transactional
    @Modifying
    @Query("UPDATE Friend f SET f.status = :status WHERE f.requesterId = :requesterId AND f.receiverId = :receiverId")
    void updateStatusByRequesterIdAndReceiverId(@Param("requesterId") Long requesterId,
                                                @Param("receiverId") Long receiverId, @Param("status") String status);

}
