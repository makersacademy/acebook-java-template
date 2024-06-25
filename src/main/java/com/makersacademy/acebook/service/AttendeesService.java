package com.makersacademy.acebook.service;

import com.makersacademy.acebook.model.Attendee;
import com.makersacademy.acebook.model.Event;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AttendeesRepository;
import com.makersacademy.acebook.repository.EventRepository;
import com.makersacademy.acebook.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.Optional;

@Service
public class AttendeesService {

    @Autowired
    private AttendeesRepository attendeesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    public void save(@ModelAttribute Attendee attendee, Long eventId, Authentication authentication) {
        User currentUser = userRepository.findByUsername(authentication.getName());
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            attendee.setUser(currentUser);
            attendee.setEvent(event);
            attendeesRepository.save(attendee);
        }
    }
}
