package com.software.pick2flick.imageprocessor.exceptions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadReadingBytesFromImageException.class)
    public ResponseEntity<String> badReadingBytesFromImageExceptionHandler(BadReadingBytesFromImageException
                                                                           exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnclearImageException.class)
    public ResponseEntity<String> unclearImageExceptionHandler(UnclearImageException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    @SneakyThrows
    public ResponseEntity<String> FeignExceptionHandler(FeignException exception) {
        HttpStatus status = HttpStatus.valueOf(exception.status());

        String responseBody = exception.contentUTF8();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonErrorMessage;
        try {
            jsonErrorMessage = mapper.readTree(responseBody);
        } catch (Exception e) {
            return new ResponseEntity<>("{\"error\": \"Bad request to models\"}", status);
        }

        String errorMessage = jsonErrorMessage.has("error") ? jsonErrorMessage.get("error").asText() :
                "Unexpected error";

        return new ResponseEntity<>("{\"error\": \"" + errorMessage + "\"}", status);
    }

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
}
