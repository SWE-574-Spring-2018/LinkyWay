/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.linkyway.controller.view.common;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author huseyin.kilic
 */
@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {
    @RequestMapping
    public String handleError(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String errorPage;
        if (response.getStatus() == HttpStatus.NOT_FOUND.value())
            errorPage = "error_not_found";
        else
            errorPage = "error_generic";

        model.addAttribute("content", errorPage);
        return "master";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}