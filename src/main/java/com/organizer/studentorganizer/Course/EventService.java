package com.organizer.studentorganizer.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) { this.eventRepository = eventRepository; }

    public List<Event> findAllEvents() { return eventRepository.findAll(); }

    public Event findEventById(Long id) {
        if (eventRepository.existsById(id))
            return eventRepository.findById(id).get();

        else return null;
    }

}


