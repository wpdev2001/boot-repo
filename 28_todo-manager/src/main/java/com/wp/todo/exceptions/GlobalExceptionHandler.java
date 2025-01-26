package com.wp.todo.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //we have to create handler methods for specific exception
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handlerNullPointerException(NullPointerException ex){
        logger.info("Its Null pointer exception from Global Handler");
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Handling resource not found exception (Creating our own exception)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
        logger.error("ERROR: {}",ex.getMessage());
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(ex.getMessage());
        response.setStatus(ex.getStatus());
        response.setSuccess(false);
        return ResponseEntity.status(ex.getStatus()).body(response);
        /*
        Can't i write it in this way:
        return ResponseEntity.status(ex.getStatus()).body(response);
        YES Ofcourse this should be the ideal apporach instead of
        --> return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
         */
    }
}
