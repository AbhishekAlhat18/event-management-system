package com.project.event_management_system.mapper;

import com.project.event_management_system.dto.AddEventDTO;
import com.project.event_management_system.model.Category;
import com.project.event_management_system.model.Event;

import org.springframework.stereotype.Component;

@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public Event toEventEntity(AddEventDTO addEventDTO, Category category) {
        Event event = new Event();
        event.setCategory(category);
        event.setTitle(addEventDTO.getTitle());
        event.setDate(addEventDTO.getDate());
        event.setStartTime(addEventDTO.getStartTime());
        event.setEndTime(addEventDTO.getEndTime());
        event.setCountry(addEventDTO.getCountry());
        event.setState(addEventDTO.getState());
        event.setCity(addEventDTO.getCity());
        event.setStreet(addEventDTO.getStreet());
        event.setVenue(addEventDTO.getVenue());
        event.setZipcode(addEventDTO.getZipcode());
        event.setDescription(addEventDTO.getDescription());
        event.setBaseTicketPrice(addEventDTO.getBaseTicketPrice());
        event.setCurrentTicketPrice(addEventDTO.getBaseTicketPrice());
        return event;
    }
}
