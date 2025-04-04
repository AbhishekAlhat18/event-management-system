package com.project.event_management_system.service;

import com.project.event_management_system.dto.AddEventDTO;
import com.project.event_management_system.dto.ApiResponseDTO;
import com.project.event_management_system.dto.EventOfferDTO;
import com.project.event_management_system.model.Event;

import java.util.List;

public interface EventService {

//    public ApiResponseDTO<Event> createEvent(AddEventDTO addEventDTO);

    public List<Event> getAllEvents();

    public Event getEventById(Long id);

    public void deleteEventById(Long id);

    public void updateEvent(Long Long, Event event);

    public List<Event> getAllEventByPrice();

    public List<Event> getEventOrganizerId(int id);
}
