package com.tempter.stoktakipservice.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tempter.stoktakipservice.advice.exceptions.EntityNotFoundException;
import com.tempter.stoktakipservice.rest.dto.MessageResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionControllerAdvice {


    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<MessageResponse> handleBadCredentials(BadCredentialsException exception) {
        return ResponseEntity.status(401).body(new MessageResponse("Bad Credentials"));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<MessageResponse> handleEntityNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity.badRequest().body(new MessageResponse(exception.getMessage()));
    }
    
    
}
