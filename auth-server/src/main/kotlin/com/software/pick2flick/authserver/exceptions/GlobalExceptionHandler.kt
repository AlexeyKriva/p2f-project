package com.software.pick2flick.authserver.exceptions

import com.software.pick2flick.authserver.exceptions.ExceptionMessage.Companion.INVALID_JSON_FORMAT
import com.software.pick2flick.authserver.exceptions.ExceptionMessage.Companion.METHOD_NOT_SUPPORTED_MESSAGE
import com.software.pick2flick.authserver.exceptions.ExceptionMessage.Companion.FAILED_REQUEST_TO_REMOTE_SERVER_MESSAGE
import feign.FeignException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException::class)
    fun userNotFoundExceptionHandler(exception: UserNotFoundException): ResponseEntity<String> {
        return ResponseEntity(exception.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(UserIsAlreadyRegisteredException::class)
    fun userIsAlreadyRegisteredExceptionHandler(exception: UserIsAlreadyRegisteredException): ResponseEntity<String> {
        return ResponseEntity(exception.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(RoleNotFoundException::class)
    fun roleNotFoundExceptionHandler(exception: RoleNotFoundException): ResponseEntity<String> {
        return ResponseEntity(exception.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun httpRequestMethodNotSupportedExceptionHandler(exception: HttpRequestMethodNotSupportedException):
            ResponseEntity<String> {
        return ResponseEntity<String>("{\"error\": ${exception.method} $METHOD_NOT_SUPPORTED_MESSAGE",
            HttpStatus.METHOD_NOT_ALLOWED)
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNoHandlerFoundException(exception: NoHandlerFoundException): ResponseEntity<String> {
        return ResponseEntity<String>(exception.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableExceptionMessageHandler(exception: HttpMessageNotReadableException?): ResponseEntity<String> {
        return ResponseEntity<String>(INVALID_JSON_FORMAT, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(FeignException::class)
    fun feignExceptionHandler(exception: FeignException): ResponseEntity<String> {
        return ResponseEntity<String>(
            "$FAILED_REQUEST_TO_REMOTE_SERVER_MESSAGE\n${exception.message}\n${exception.contentUTF8()}",
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
}