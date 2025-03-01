package com.wp.estore.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ApiResponseMessage {
    private String message;
    private boolean success;
    private HttpStatus status;
}
