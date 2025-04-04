package com.project.event_management_system.dto.payment_service;

import com.project.event_management_system.encryption.AESEncryptionUtil;
import com.project.event_management_system.enums.AccountType;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizerBankAccountDetailDTO {

    @NotNull(message = "User ID cannot be null.")
    private Long userId;

    @NotBlank(message = "Account holder name is required.")
    @Size(min = 3, max = 100, message = "Account holder name must be between 3 and 100 characters.")
    private String accountHolderName;

    @NotBlank(message = "Account number is required.")
    @Pattern(regexp = "^[0-9]{10,20}$", message = "Account number must be between 10 and 20 digits.")
    private String encryptedAccountNumber;

    @NotBlank(message = "Routing number is required.")
    @Pattern(regexp = "^[0-9]{9}$", message = "Routing number must be exactly 9 digits.")
    private String encryptedRoutingNumber;

    @NotBlank(message = "Bank name is required.")
    @Size(min = 3, max = 100, message = "Bank name must be between 3 and 100 characters.")
    private String bankName;

    @NotNull(message = "Account type cannot be null.")
    private AccountType accountType;

    @NotNull(message = "Date of birth cannot be null.")
    private LocalDate dob;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email should be valid.")
    private String email;

    @NotBlank(message = "Phone number is required.")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be between 10 and 15 digits and may start with a '+'.")
    private String phoneNumber;

    @NotBlank(message = "Address line 1 is required.")
    @Size(min = 5, max = 100, message = "Address line 1 must be between 5 and 100 characters.")
    private String addressLine1;

    private String addressLine2;

    @NotBlank(message = "Country is required.")
    private String country;

    @NotBlank(message = "City is required.")
    private String city;

    @NotBlank(message = "State is required.")
    private String state;

    @NotBlank(message = "Zip code is required.")
    @Pattern(regexp = "^[0-9]{5,10}$", message = "Zip code must be between 5 and 10 digits.")
    private String zipCode;


    public void encryptSensitiveData() {
        try {
            this.encryptedAccountNumber = AESEncryptionUtil.encrypt(this.encryptedAccountNumber);
            this.encryptedRoutingNumber = AESEncryptionUtil.encrypt(this.encryptedRoutingNumber);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data", e);
        }
    }
}

