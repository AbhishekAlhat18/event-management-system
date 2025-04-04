package com.project.event_management_system.service;

import com.project.event_management_system.dto.ApiResponseDTO;
import com.project.event_management_system.dto.OrganizerDetailDTO;
import com.project.event_management_system.model.User;

public interface OrganizerService {
    ApiResponseDTO<Void> createOrganizer(OrganizerDetailDTO organizerDetailDTO);
}
