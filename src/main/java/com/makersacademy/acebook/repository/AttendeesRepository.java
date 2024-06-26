package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Attendee;
import com.makersacademy.acebook.model.Event;
import com.makersacademy.acebook.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttendeesRepository extends CrudRepository<Attendee, Long> {
    public Long countByEvent (Event event);
    public Attendee findByUserAndEvent(User user, Event event);
}
