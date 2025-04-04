package com.project.event_management_system.service.payment_service;

import com.project.event_management_system.dto.ApiResponseDTO;
import com.project.event_management_system.dto.payment_service.OrganizerBankAccountDetailDTO;
import org.springframework.http.ResponseEntity;

public interface PaymentServiceClient {
    ApiResponseDTO<String> addBankAccountDetail(OrganizerBankAccountDetailDTO organizerBankAccountDetailDTO);
}
