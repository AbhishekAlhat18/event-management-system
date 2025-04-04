package com.project.event_management_system.config.payment_service;

import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "payment.service")
@Validated
public class PaymentServiceConfig {
    @NotBlank  private String url;
    @NotBlank private String apiKey;

    // Getters and setters (required for @ConfigurationProperties)
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getApiKey() { return apiKey; }
    public void setApiKey(String apiKey) { this.apiKey = apiKey; }
}
