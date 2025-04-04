package com.project.event_management_system.controller.payment_service;

import com.project.event_management_system.dto.ApiResponseDTO;
import com.project.event_management_system.dto.payment_service.OrganizerBankAccountDetailDTO;
import com.project.event_management_system.service.payment_service.PaymentServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/organizers")
public class OrganizerBankAccountDetailController {

    private final PaymentServiceClient paymentServiceClient;

    public OrganizerBankAccountDetailController(PaymentServiceClient paymentServiceClient) {
        this.paymentServiceClient = paymentServiceClient;
    }

    @PostMapping("/bank-details")
    public ResponseEntity<ApiResponseDTO<String>> addBankAccount(@RequestBody OrganizerBankAccountDetailDTO organizerBankAccountDetailDTO) {
        organizerBankAccountDetailDTO.encryptSensitiveData();
        ApiResponseDTO<String> response = this.paymentServiceClient.addBankAccountDetail(organizerBankAccountDetailDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}

