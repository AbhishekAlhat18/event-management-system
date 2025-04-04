package com.project.event_management_system.controller;

import com.project.event_management_system.dto.ApiResponseDTO;
import com.project.event_management_system.dto.OrganizerDetailDTO;
import com.project.event_management_system.service.OrganizerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final OrganizerService organizerService;

    @Autowired
    public UserController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @PostMapping("/organizer-detail")
    public ResponseEntity<ApiResponseDTO<Void>> createOrganizerDetail(@RequestBody @Valid OrganizerDetailDTO createOrganizerDetailRequest) {
        ApiResponseDTO<Void> response = this.organizerService.createOrganizer(createOrganizerDetailRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
