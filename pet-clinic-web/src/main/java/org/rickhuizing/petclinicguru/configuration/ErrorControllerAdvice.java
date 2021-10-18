package org.rickhuizing.petclinicguru.configuration;

import org.rickhuizing.petclinicguru.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundException(NotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error/notFound";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String internalServerError(Exception ex, Model model) {
        ex.printStackTrace(); // todo add logging
        model.addAttribute("message", "Unexpected error occurred");
        return "error/error";
    }
}
