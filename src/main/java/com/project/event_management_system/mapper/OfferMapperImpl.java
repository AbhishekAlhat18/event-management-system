package com.project.event_management_system.mapper;


//@Component
//public class OfferMapperImpl implements OfferMapper{
//    private final OrganizerRepository organizerRepository;
//
//    @Autowired
//    public OfferMapperImpl(OrganizerRepository organizerRepository) {
//        this.organizerRepository = organizerRepository;
//    }
//
//
//    @Override
//    public Offer toOfferEntity(AddOfferDTO addOfferDTO) {
//        Offer offer = new Offer();
//        Organizer organizer = organizerRepository.findById(addOfferDTO.getOrganizerId())
//                              .orElseThrow(()->new OrganizerNotFoundException("Organizer with id: "+addOfferDTO.getOrganizerId()+ " not found"));
//        offer.setOrganizer(organizer);
//        offer.setName(addOfferDTO.getName());
//        return offer;
//    }
//
//}
