package com.project.event_management_system.mapper;

import com.project.event_management_system.dto.OrganizerRegistrationDTO;
import com.project.event_management_system.dto.UserRegistrationDTO;
import com.project.event_management_system.model.Organizer;
import com.project.event_management_system.model.User;

public interface PersonMapper {

    User toUserEntity(UserRegistrationDTO dto);

    Organizer toOrganizerEntity(OrganizerRegistrationDTO dto);

}
