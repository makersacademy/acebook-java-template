package com.makersacademy.acebook.service;

import com.makersacademy.acebook.model.Event;
import com.makersacademy.acebook.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SearchService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> searchEvents(String keyword) {
        return eventRepository.searchEventsByTitleOrUsernameOrLocation(keyword);
    }
//    CrudRepository.findAll() method returns an Iterable<Event>, but our method signature expects a List<Event>
//    convert the Iterable<Event> to a Stream<Event>, then collect it into a List<Event>
    public List<Event> getAllEvents() {
        Iterable<Event> iterable = eventRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
}


}
