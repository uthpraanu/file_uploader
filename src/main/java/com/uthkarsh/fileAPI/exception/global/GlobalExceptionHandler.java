package com.uthkarsh.fileAPI.exception.global;

import com.uthkarsh.fileAPI.exception.FileNotFoundException;
import com.uthkarsh.fileAPI.exception.RepositoryException;
import com.uthkarsh.fileAPI.exception.ServiceFailedException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
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

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<?> handleRepositoryException(RepositoryException ex){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> handleFileNotFoundException(FileNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

}
