package com.project.event_management_system.controller;

import com.project.event_management_system.dto.AddOfferDTO;
import com.project.event_management_system.dto.ApiResponseDTO;
import com.project.event_management_system.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizer")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

//    @PostMapping(path = "/add-offer")
//    public ResponseEntity<ApiResponseDTO<Void>> createOffer(@RequestBody @Valid AddOfferDTO createOfferRequest){
//        ApiResponseDTO<Void> response = this.offerService.createOffer(createOfferRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
}
