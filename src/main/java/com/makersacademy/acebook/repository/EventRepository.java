package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {



    // Method to find events by a specific scheduledDate
    List<Event> findByScheduledDateBetween(Date minScheduledDate, Date maxScheduledDate);
    // Searching term
    @Query("SELECT e FROM Event e WHERE LOWER(e.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(e.user.username) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(e.location) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Event> searchEventsByTitleOrUsernameOrLocation(@Param("keyword") String keyword);
    // Method to fetch all events ordered by scheduledDate in descending order
    List<Event> findAllByOrderByScheduledDateDesc();}
