package com.wp.todo.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExpResponse> ResourceNotFoundHandler(ResourceNotFound ex){
        ExpResponse response = new ExpResponse();
        response.setMessage(ex.getMessage());
        response.setHttpStatus(ex.getStatus());
        response.setStatus(false);
        return ResponseEntity.status(ex.getStatus()).body(response);
    }

}
