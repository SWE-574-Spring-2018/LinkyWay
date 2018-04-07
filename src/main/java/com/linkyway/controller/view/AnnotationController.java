/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.linkyway.controller.view;

import com.linkyway.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huseyin.kilic
 */
@Controller
@RequestMapping("/annotation")
public class AnnotationController extends BaseController {
    @RequestMapping("/discover")
    public String annotationDiscoverView(Model model) {
        model.addAttribute("content", "annotation_discover");
        return "master";
    }

    @RequestMapping("/create")
    public String annotationCreateView(Model model) {
        model.addAttribute("content", "annotation_create");
        return "master";
    }
}