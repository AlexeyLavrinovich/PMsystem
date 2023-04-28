package com.PMsystem.controller;

import com.PMsystem.exception.AlreadyExistsException;
import com.PMsystem.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public String handleAlreadyExistsException(
            AlreadyExistsException alreadyExistsException,
            Model model
    ) {
        log.error("AlreadyExistsException: {}", alreadyExistsException.getMessage());
        model.addAttribute("error", alreadyExistsException.getMessage());
        return "error";
    }

    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(
            NotFoundException notFoundException,
            Model model
    ) {
        log.error("NotFoundException: {}", notFoundException.getMessage());
        model.addAttribute("error", notFoundException.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(
            Exception exception,
            Model model
    ) {
        log.error("Exception: {}", exception.getMessage());
        model.addAttribute("error", exception.getMessage());
        return "error";
    }
}
