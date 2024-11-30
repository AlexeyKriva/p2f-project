package com.software.pick2flick.nlpmicroservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.software.pick2flick.nlpmicroservice.exceptions.ExceptionMessage.BAD_REQUEST_MESSAGE;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> httpMessageNotReadableExceptionHandle(HttpMessageNotReadableException
                                                                        exception) {
        return new ResponseEntity<>("{\"error\": \"" + exception.getMessage() + "\"}", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> httpRequestMethodNotSupportedExceptionHandle(HttpRequestMethodNotSupportedException
                                                                               exception) {
        return new ResponseEntity<>("{\"error\": \"" + exception.getMessage() + "\"}",
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandle(Exception
                                                                                       exception) {
        return new ResponseEntity<>(BAD_REQUEST_MESSAGE,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}