package com.project.event_management_system.mapper;

import com.project.event_management_system.dto.OrganizerDetailDTO;
import com.project.event_management_system.model.Organizer;
import com.project.event_management_system.model.User;

import org.springframework.stereotype.Component;

@Component
public class OrganizerMapperImplementation implements OrganizerMapper{

    @Override
    public Organizer mapToOrganizer(OrganizerDetailDTO organizerDetailDTO, User user) {


        Organizer organizer = new Organizer();
        organizer.setUser(user);
        organizer.setDisplayName(organizerDetailDTO.getDisplayName());
        organizer.setCountry(organizerDetailDTO.getCountry());
        organizer.setState(organizerDetailDTO.getState());
        organizer.setCity(organizerDetailDTO.getCity());
        organizer.setAddressLine1(organizerDetailDTO.getAddressLine1());
        organizer.setAddressLine2(organizerDetailDTO.getAddressLine2());
        organizer.setZipCode(organizerDetailDTO.getZipCode());
        organizer.setPhoneNumber(organizerDetailDTO.getPhoneNumber());
        organizer.setWebsiteUrl(organizerDetailDTO.getWebsiteUrl());
        organizer.setBioDescription(organizerDetailDTO.getBioDescription());
        return organizer;

    }
}
