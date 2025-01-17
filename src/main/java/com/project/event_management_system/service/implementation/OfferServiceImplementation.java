package com.project.event_management_system.service.implementation;

import com.project.event_management_system.model.Offer;
import com.project.event_management_system.repository.OfferRepository;
import com.project.event_management_system.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImplementation implements OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImplementation(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }


    @Override
    public Offer createOffer(Offer offer) {
        return this.offerRepository.save(offer);
    }

    @Override
    public List<Offer> getAllOffer() {
        return this.offerRepository.findAll();
    }

    @Override
    public Offer getOfferById(int id) {
        return this.offerRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteOfferById(int id) {
        this.offerRepository.deleteById(id);
    }

    @Override
    public Offer updateOffer(int id, Offer offer) {
        Offer existingOffer = offerRepository.findById(id).get();
        if(existingOffer.getOfferName()!=null)
            existingOffer.setOfferName(offer.getOfferName());
        if(existingOffer.getOfferDescription()!=null)
            existingOffer.setOfferDescription(offer.getOfferDescription());
        return offerRepository.save(existingOffer);
    }

    @Override
    public List<Offer> getByOfferId(int id) {
        return offerRepository.getOfferById(id);
    }
}
