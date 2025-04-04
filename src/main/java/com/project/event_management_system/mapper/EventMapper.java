package com.project.event_management_system.mapper;

import com.project.event_management_system.dto.AddEventDTO;
import com.project.event_management_system.model.Category;
import com.project.event_management_system.model.Event;


public interface EventMapper {
    Event toEventEntity(AddEventDTO addEventDTO, Category category);
}
