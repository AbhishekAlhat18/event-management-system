package com.project.event_management_system.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(NON_NULL)
public class ApiResponseDTO<T> {
    private final String message;
    private final HttpStatus status;
    private final int statusCode;
    private final String timestamp;
    private final T data;

}

