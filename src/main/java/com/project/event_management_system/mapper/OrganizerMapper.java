package com.project.event_management_system.mapper;

import com.project.event_management_system.dto.OrganizerDetailDTO;
import com.project.event_management_system.model.Organizer;
import com.project.event_management_system.model.User;

public interface OrganizerMapper {

    Organizer mapToOrganizer(OrganizerDetailDTO organizerDetailDTO, User user);
}
