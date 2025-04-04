package com.project.event_management_system.service.implementation;

import com.project.event_management_system.dto.ApiResponseDTO;
import com.project.event_management_system.dto.OrganizerDetailDTO;
import com.project.event_management_system.enums.Role;
import com.project.event_management_system.exception.user.UserNotFoundException;
import com.project.event_management_system.mapper.OrganizerMapper;
import com.project.event_management_system.model.Organizer;
import com.project.event_management_system.model.User;
import com.project.event_management_system.repository.OrganizerRepository;
import com.project.event_management_system.repository.UserRepository;
import com.project.event_management_system.security.UserPrinciple;
import com.project.event_management_system.service.OrganizerService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrganizerServiceImplementation implements OrganizerService {

    private final OrganizerMapper organizerMapper;
    private final UserRepository userRepository;
    private final OrganizerRepository organizerRepository;

    public OrganizerServiceImplementation(OrganizerMapper organizerMapper, UserRepository userRepository, OrganizerRepository organizerRepository) {
        this.organizerMapper = organizerMapper;
        this.userRepository = userRepository;
        this.organizerRepository = organizerRepository;
    }

    @Override
    public ApiResponseDTO<Void> createOrganizer(OrganizerDetailDTO organizerDetailDTO) {

        // Retrieve the currently authenticated user
        UserPrinciple authenticatedUser = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findByEmail(authenticatedUser.getUsername())
                .orElseThrow(() -> new UserNotFoundException("Authenticated user with email '"+ authenticatedUser.getUsername()+ "' not found"));


        Organizer organizer = this.organizerMapper.mapToOrganizer(organizerDetailDTO, user);

        this.organizerRepository.save(organizer);

        //user.setRoles(Set.of(Role.ORGANIZER));

        user.getRoles().add(Role.ORGANIZER);

        this.userRepository.save(user);

        return ApiResponseDTO.<Void>builder()
                .message("Organizer Details Submitted Successfully!")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now().toString())
                .build();
    }
}
