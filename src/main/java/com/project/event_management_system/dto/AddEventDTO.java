package com.project.event_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AddEventDTO {


    @NotNull
    private Long categoryId;

    @NotBlank
    private String title;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    @NotBlank
    private String country;

    @NotBlank
    private String state;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    @NotBlank
    private String venue;

    @NotNull
    private int zipcode;

    @NotBlank
    private String description;

    @NotNull
    private int totalSeats;

    @NotNull
    private double baseTicketPrice;

    private List<EventOfferDTO> eventOffers;


}
