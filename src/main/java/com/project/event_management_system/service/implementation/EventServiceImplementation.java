package com.project.event_management_system.service.implementation;

import com.project.event_management_system.model.Event;
import com.project.event_management_system.repository.EventRepository;
import com.project.event_management_system.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImplementation implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImplementation(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(Event event) {
        return this.eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return this.eventRepository.findAll();
    }

    @Override
    public Event getEventById(int id) {
        return this.eventRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteEventById(int id) {
        this.eventRepository.deleteById(id);
    }

    @Override
    public Event updateEvent(int id, Event event) {
        Event existingEvent = eventRepository.findById(id).get();
        if (event.getEventName() != null)
            existingEvent.setEventName(event.getEventName());
        if (event.getEventPrice() != 0)
            existingEvent.setEventPrice(event.getEventPrice());
        if (event.getEventLocation() != null)
            existingEvent.setEventLocation(event.getEventLocation());
        if (event.getEventDate() != null)
            existingEvent.setEventDate(event.getEventDate());
        if (event.getEventDetails() != null)
            existingEvent.setEventDetails(event.getEventDetails());
        return eventRepository.save(existingEvent);

    }

    @Override
    public List<Event> getAllEventByPrice() {
        return this.eventRepository.findAll(Sort.by(Sort.Direction.DESC, "eventPrice"));
    }

    @Override
    public List<Event> getEventOrganizerId(int id) {
        return this.eventRepository.getByOrganizerId(id);
    }
}
