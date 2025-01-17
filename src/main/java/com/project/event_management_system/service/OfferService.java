package com.project.event_management_system.service;

import com.project.event_management_system.model.Offer;

import java.util.List;

public interface OfferService {

    public Offer createOffer(Offer offer);

    public List<Offer> getAllOffer();

    public Offer getOfferById(int id);

    public void deleteOfferById(int id);

    public Offer updateOffer(int id, Offer offer);

    public List<Offer> getByOfferId(int id);

}
