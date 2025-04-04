package com.project.event_management_system.mapper;

import com.project.event_management_system.dto.AddEventDTO;
import com.project.event_management_system.exception.offer.OfferNotFoundException;
import com.project.event_management_system.model.Event;
import com.project.event_management_system.model.EventOffer;
import com.project.event_management_system.model.Offer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventOfferMapperImpl implements EventOfferMapper{
    @Override
    public List<EventOffer> toEventOfferMapper(AddEventDTO addEventDTO, Event event, List<Offer> offers) {
        return addEventDTO.getEventOffers().stream().map(eventOfferDTO -> {
            // Find matching offer from the list
            Offer matchingOffer = offers.stream()
                    .filter(offer -> offer.getId() == (eventOfferDTO.getOfferId()))
                    .findAny()
                    .orElseThrow(() -> new OfferNotFoundException("Offer ID " + eventOfferDTO.getOfferId() + " not found for this organizer"));

            // Create EventOffer entity and set all required fields
            EventOffer eventOffer = new EventOffer();
            eventOffer.setEvent(event);
            eventOffer.setOffer(matchingOffer);
            eventOffer.setDescription(eventOfferDTO.getOfferDescription());
            eventOffer.setStartDate(eventOfferDTO.getOfferStartDate());
            eventOffer.setEndDate(eventOfferDTO.getOfferEndDate());
            eventOffer.setDiscountPercentage(eventOfferDTO.getOfferDiscountPercentage());


            return eventOffer;
        }).collect(Collectors.toList());
    }
}
