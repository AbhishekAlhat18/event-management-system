package com.project.event_management_system.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AddOfferDTO {
    @NotBlank(message = "Offer name is required")
    private String name;

    @NotNull(message = "Organizer Id is required")
    private Long organizerId;

}
