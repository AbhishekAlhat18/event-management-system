package com.project.event_management_system.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventOfferDTO {
    @NotNull(message = "Offer id is required")
    private Integer offerId;

    @NotBlank(message = "Description is required ")
    private String offerDescription;

    @NotNull(message = "Start date is required")
    private LocalDate offerStartDate;

    @NotNull(message = "End date is required")
    private LocalDate offerEndDate;

    @Min(value = 0, message = "Discount percentage must be at least 0%")
    @Max(value = 100, message = "Discount percentage cannot exceed 100%")
    private double offerDiscountPercentage;

    @NotNull(message = "Organizer ID is required")
    private Long organizerId;

}
