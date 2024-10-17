package com.lucasbandeira.workshopmongo.resources.exception;

import com.lucasbandeira.workshopmongo.services.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity <StandardError> objectNotFound( ObjectNotFoundException exception, HttpServletRequest request ) {
        StandardError standardError = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), "Not found", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }
}
