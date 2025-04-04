package com.project.event_management_system.mapper;

import com.project.event_management_system.dto.AddOfferDTO;
import com.project.event_management_system.model.Offer;

public interface  OfferMapper {
    Offer toOfferEntity(AddOfferDTO addOfferDTO);
}
