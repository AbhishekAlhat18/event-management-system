package com.project.event_management_system.controller;

import com.project.event_management_system.dto.AddEventDTO;
import com.project.event_management_system.dto.ApiResponseDTO;
import com.project.event_management_system.model.Event;
import com.project.event_management_system.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organizer")
public class EventController {

//    private final EventService eventService;
//
//    public EventController(EventService eventService) {
//        this.eventService = eventService;
//    }
//
//    @PostMapping(path = "/add-event")
//    public ResponseEntity<ApiResponseDTO<Event>> createEvent(@RequestBody @Valid AddEventDTO createEventRequest){
//        ApiResponseDTO<Event> response = this.eventService.createEvent(createEventRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
}
