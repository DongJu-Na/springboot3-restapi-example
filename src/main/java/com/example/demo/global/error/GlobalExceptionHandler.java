package com.example.demo.global.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
    HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        final ErrorResponse errorResponse = ErrorResponse.of("Validation failed",
                LocalDateTime.now(), exception.getBindingResult().toString());
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
            public ResponseEntity<Object> handleServerException(Exception exception, WebRequest webRequest) {
        final ErrorResponse errorResponse = ErrorResponse.of(exception.getMessage(),
                LocalDateTime.now(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
    }

}