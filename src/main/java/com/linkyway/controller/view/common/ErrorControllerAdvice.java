/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.linkyway.controller.view.common;

import org.springframework.social.NotAuthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author huseyin.kilic
 */
@ControllerAdvice
public class ErrorControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = NotAuthorizedException.class)
    public String handleUnauthorizedException() {
        return "redirect:/login";
    }
}