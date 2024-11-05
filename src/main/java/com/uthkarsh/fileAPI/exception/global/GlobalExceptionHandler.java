package com.uthkarsh.fileAPI.exception.global;

import com.uthkarsh.fileAPI.exception.ServiceFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceFailedException.class)
    public ResponseEntity<?> handleServiceFailedException(ServiceFailedException ex){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());
    }

}
