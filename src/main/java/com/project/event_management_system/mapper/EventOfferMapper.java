package com.project.event_management_system.mapper;

import com.project.event_management_system.dto.AddEventDTO;
import com.project.event_management_system.dto.EventOfferDTO;
import com.project.event_management_system.model.Event;
import com.project.event_management_system.model.EventOffer;
import com.project.event_management_system.model.Offer;

import java.util.List;

public interface EventOfferMapper {
    List<EventOffer> toEventOfferMapper(AddEventDTO addEventDTO, Event event, List<Offer> offers);


}
