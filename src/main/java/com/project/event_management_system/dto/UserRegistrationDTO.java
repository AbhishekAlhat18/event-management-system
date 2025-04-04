package com.project.event_management_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDTO {

    @NotBlank(message = "First name is required")
    @Size(min = 2, message = "First name should have at least 2 characters")
    @Size(max = 50, message = "First name should not exceed 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, message = "Last name should have at least 2 characters")
    @Size(max = 50, message = "Last name should not exceed 50 characters")
    private String lastName;

    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is Mandatory")
    @Size(max = 100, message = "Email should not exceed 100 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,18}$",
            message = "Password must be 8-18 characters and include a digit, lowercase, uppercase, and special character."
    )
    private String password;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "\\d{10}", message = "Invalid phone number format")
    private String contactNumber;
}
