package com.project.event_management_system.service.payment_service.implementation;
import com.project.event_management_system.config.payment_service.PaymentServiceConfig;
import com.project.event_management_system.dto.ApiResponseDTO;
import com.project.event_management_system.dto.payment_service.OrganizerBankAccountDetailDTO;
import com.project.event_management_system.service.payment_service.PaymentServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class PaymentServiceClientImpl implements PaymentServiceClient {

    private final RestTemplate restTemplate;
    private final PaymentServiceConfig paymentServiceConfig;

    @Autowired
    public PaymentServiceClientImpl(RestTemplate restTemplate, PaymentServiceConfig paymentServiceConfig) {
        this.restTemplate = restTemplate;
        this.paymentServiceConfig = paymentServiceConfig;
    }


    public ApiResponseDTO<String> addBankAccountDetail(OrganizerBankAccountDetailDTO organizerBankAccountDetailDTO) {
        // Attach API key
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", paymentServiceConfig.getApiKey());

        // Send request to Payment Service
        HttpEntity<OrganizerBankAccountDetailDTO> request = new HttpEntity<>(organizerBankAccountDetailDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(paymentServiceConfig.getUrl(), HttpMethod.POST, request, String.class);

        return ApiResponseDTO.<String>builder()
                .message("Bank account detail submitted to Payment Service for verification!")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(response.getBody())   //change this later to match the response from the payment service
                .timestamp(LocalDateTime.now().toString())
                .build();
    }
}


