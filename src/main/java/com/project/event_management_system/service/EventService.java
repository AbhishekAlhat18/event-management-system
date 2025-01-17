package com.project.event_management_system.service;

import com.project.event_management_system.model.Event;

import java.util.List;

public interface EventService {

    public Event createEvent(Event event);

    public List<Event> getAllEvents();

    public Event getEventById(int id);

    public void deleteEventById(int id);

    public Event updateEvent(int id, Event event);

    public List<Event> getAllEventByPrice();

    public List<Event> getEventOrganizerId(int id);
}
