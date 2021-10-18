package org.rickhuizing.petclinicguru.controllers;

import org.rickhuizing.petclinicguru.exceptions.NotFoundException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest req) {
        Object statusCode = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode instanceof Integer) {
            HttpStatus status = HttpStatus.resolve((Integer) statusCode);

            if (status != null) {
                return switch (status) {
                    case NOT_FOUND -> throw new NotFoundException("Page not found");
                    default -> "error/error";
                };
            }
        }
        return "error/error";
    }
}
