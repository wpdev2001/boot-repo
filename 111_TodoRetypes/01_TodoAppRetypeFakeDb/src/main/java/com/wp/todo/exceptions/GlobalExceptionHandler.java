package com.wp.todo.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler{
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //creating Handler for ResourceNotFound
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
        logger.error("ERROR: {}", ex.getMessage());
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(ex.getMessage());
        response.setSuccess(false);
        response.setStatus(ex.getStatus());
        return ResponseEntity.status(ex.getStatus()).body(response);
    }
}
