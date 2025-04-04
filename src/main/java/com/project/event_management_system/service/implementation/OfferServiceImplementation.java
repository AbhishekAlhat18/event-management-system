package com.project.event_management_system.service.implementation;

import com.project.event_management_system.service.OfferService;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImplementation implements OfferService {
//
//    private final OfferRepository offerRepository;
//    private final OfferMapper offerMapper;
//
//    @Autowired
//    public OfferServiceImplementation(OfferRepository offerRepository,OfferMapper offerMapper) {
//        this.offerRepository = offerRepository;
//        this.offerMapper = offerMapper;
//    }
//
//
//    @Override
//    public ApiResponseDTO<Void> createOffer (AddOfferDTO addOfferDTO) {
//
//        if(this.offerRepository.existsByNameAndOrganizerId(addOfferDTO.getName(),addOfferDTO.getOrganizerId())){
//            throw new OfferAlreadyExistsException("Offer with name '"+addOfferDTO.getName()+ "' already exists for organizer with id: " +addOfferDTO.getOrganizerId());
//        }
//
//        Offer offer = this.offerMapper.toOfferEntity(addOfferDTO);
//
//        this.offerRepository.save(offer);
//
//        return ApiResponseDTO.<Void>builder()
//                .message("Offer Successfully added!")
//                .status(HttpStatus.CREATED)
//                .statusCode(HttpStatus.CREATED.value())
//                .timestamp(LocalDateTime.now().toString())
//                .build();
//    }
//
//    @Override
//    public List<Offer> getAllOffer() {
//        return this.offerRepository.findAll();
//    }
//
//    @Override
//    public Offer getOfferById(int id) {
//        return this.offerRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public void deleteOfferById(int id) {
//        this.offerRepository.deleteById(id);
//    }
//
//    @Override
//    public void updateOffer(int id, Offer offer) {
//
//
//    }


}
