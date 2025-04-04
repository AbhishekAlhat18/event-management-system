package com.project.event_management_system.service.implementation;

import com.project.event_management_system.mapper.EventMapper;
import com.project.event_management_system.mapper.EventOfferMapper;
import com.project.event_management_system.model.*;
import com.project.event_management_system.repository.*;
import com.project.event_management_system.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImplementation implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final OfferRepository offerRepository;
    private final CategoryRepository categoryRepository;
    private final EventMapper eventMapper;
    private final EventOfferMapper eventOfferMapper;

    @Autowired
    public EventServiceImplementation(EventRepository eventRepository, UserRepository userRepository, OfferRepository offerRepository, CategoryRepository categoryRepository, EventMapper eventMapper, EventOfferMapper eventOfferMapper) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
        this.categoryRepository = categoryRepository;
        this.eventMapper = eventMapper;
        this.eventOfferMapper = eventOfferMapper;
    }
//
//    @Override
//    @Transactional
//    public ApiResponseDTO<Event> createEvent(AddEventDTO addEventDTO) {
//
//        // Retrieve the currently authenticated user
//        CustomUserDetails authenticatedUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        // Fetch the Person entity using the email from CustomUserDetails (since email is unique)
//        User authenticatedPerson = personRepository.findByEmail(authenticatedUser.getUsername())
//                .orElseThrow(() -> new OrganizerNotFoundException("Authenticated user not found"));
//
//        // Now, cast the person to Organizer (since it's authenticated as an organizer)
//        Organizer organizer = (Organizer) authenticatedPerson;
//
////        Organizer organizer = organizerRepository.findById(addEventDTO.getOrganizerId())
////                .orElseThrow(() -> new OrganizerNotFoundException("Organizer with ID: " + addEventDTO.getOrganizerId() + " not found"));
//
//        Category category = categoryRepository.findById(addEventDTO.getCategoryId())
//                .orElseThrow(() -> new CategoryNotFoundException("Category with ID: " + addEventDTO.getCategoryId() + " not found"));
//
//        Event event = eventMapper.toEventEntity(addEventDTO,  category);
//
//        // Process event offers
//        if (!addEventDTO.getEventOffers().isEmpty()) {
//            List<EventOffer> eventOffers = processEventOffers(addEventDTO, organizer, event);
//            event.setEventOffers(eventOffers); // Why this is important
//
//        }
//
//        // Save event (cascades event offers if properly mapped)
//        eventRepository.save(event);
//
//        return ApiResponseDTO.<Event>builder()
//                .message("Event successfully added!")
//                .status(HttpStatus.CREATED)
//                .statusCode(HttpStatus.CREATED.value())
//                .timestamp(LocalDateTime.now().toString())
//                .data(event)
//                .build();
//    }
//
//    private List<EventOffer> processEventOffers(AddEventDTO addEventDTO, Organizer organizer, Event event) {
//        List<Integer> offerIds = addEventDTO.getEventOffers().stream()
//                .map(EventOfferDTO::getOfferId)
//                .toList();
//
//        List<Offer> offers = offerRepository.findByIdInAndOrganizerId(offerIds, organizer.getId());
//
//        if (offers.size() != offerIds.size()) {
//            throw new OfferNotFoundException("One or more offers do not exist for the given organizer.");
//        }
//
//        // Convert DTOs to EventOffer entities
//        return eventOfferMapper.toEventOfferMapper(addEventDTO, event, offers);
//    }



    @Override
    public List<Event> getAllEvents() {
        return this.eventRepository.findAll();
    }

    @Override
    public Event getEventById(Long id) {
        return this.eventRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteEventById(Long id) {
        this.eventRepository.deleteById(id);
    }

    @Override
    public void updateEvent(Long id, Event event) {

    }

    @Override
    public List<Event> getAllEventByPrice() {
        return this.eventRepository.findAll(Sort.by(Sort.Direction.DESC, "eventPrice"));
    }

    @Override
    public List<Event> getEventOrganizerId(int id) {
        return this.eventRepository.getByOrganizerId(id);
    }
}
