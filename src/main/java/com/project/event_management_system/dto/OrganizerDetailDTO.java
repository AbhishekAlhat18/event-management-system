package com.project.event_management_system.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizerDetailDTO {

    @NotBlank(message = "Display name is required")
    @Size(min = 2, max = 100, message = "Display name must be between 2-100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 &.'-]+$", message = "Display name contains invalid characters")
    private String displayName;

    @NotBlank(message = "Country is required")
    @Size(max = 100, message = "Country name too long")
    private String country;

    @NotBlank(message = "State is required")
    @Size(min = 2, max = 2, message = "Use 2-letter state code")
    private String state;

    @NotBlank(message = "City is required")
    @Size(max = 100, message = "City name too long")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "City can only contain letters and spaces")
    private String city;

    @NotBlank(message = "Address line 1 is required")
    @Size(max = 255, message = "Address line 1 too long")
    @Pattern(regexp = "^[0-9]+\\s[a-zA-Z0-9\\s.#-]+$", message = "Invalid address format. Use '123 Main St' format")
    private String addressLine1;

    @Size(max = 255, message = "Address line 2 too long")
    @Pattern(regexp = "^[a-zA-Z0-9\\s.#-]*$", message = "Invalid address format")
    private String addressLine2;

    @NotBlank(message = "ZIP code is required")
    @Pattern(regexp = "^\\d{5}(-\\d{4})?$", message = "Invalid ZIP code format. Use 12345 or 12345-6789")
    private String zipCode;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$",
            message = "Invalid phone number format. Use 123-456-7890")
    private String phoneNumber;

    @Size(max = 100, message = "Website URL too long")
    @Pattern(regexp = "^(https?:\\/\\/)?([\\w-]+\\.)+[\\w-]+(\\/[\\w- .\\/?%&=]*)?$",
            message = "Invalid website URL format")
    private String websiteUrl;

    @Size(max = 500, message = "Bio description too long")
    private String bioDescription;


    //Why use this?
    // Pre-process website URL before validation
//    @AssertTrue(message = "Website URL must use HTTPS")
//    public boolean isWebsiteUrlSecure() {
//        if (websiteUrl == null || websiteUrl.isEmpty()) {
//            return true;
//        }
//        // Automatically prepend https:// if missing
//        if (!websiteUrl.matches("^https?://.*")) {
//            websiteUrl = "https://" + websiteUrl;
//        }
//        return websiteUrl.startsWith("https://");
//    }
}
