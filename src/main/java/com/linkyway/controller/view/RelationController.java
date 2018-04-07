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
@RequestMapping("/relation")
public class RelationController extends BaseController {
    @RequestMapping("/discover")
    public String relationDiscoverView(Model model) {
        model.addAttribute("content", "relation_discover");
        return "master";
    }

    @RequestMapping("/create")
    public String relationCreateView(Model model) {
        model.addAttribute("content", "relation_create");
        return "master";
    }
}