package com.project.event_management_system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organizers")
public class OrganizerController {

    @GetMapping(path = "/dashboard")
    public ResponseEntity<String> getAllCreatedEvents(){
        return ResponseEntity.status(HttpStatus.OK).body(" Organizer Dashboard");
    }
}
