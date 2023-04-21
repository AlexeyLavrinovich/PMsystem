package com.PMsystem.controller;

import com.PMsystem.exception.AlreadyExistsException;
import com.PMsystem.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExistsException(AlreadyExistsException alreadyExistsException) {
        log.error("AlreadyExistsException: {}", alreadyExistsException.getMessage());
        return ResponseEntity.badRequest().body(alreadyExistsException.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException notFoundException) {
        log.error("NotFoundException: {}", notFoundException.getMessage());
        return ResponseEntity.badRequest().body(notFoundException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        log.error("Exception: {}", exception.getMessage());
        return ResponseEntity.badRequest().body("Something bad happened...");
    }
}
