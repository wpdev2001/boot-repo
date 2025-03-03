package com.wp.estore.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceNotFoundException extends RuntimeException{
    private String message;
}
