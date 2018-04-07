/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.linkyway.controller.view;

import com.linkyway.controller.base.BaseController;
import com.linkyway.model.domain.Node;
import com.linkyway.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author huseyin.kilic
 */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {
    @Autowired
    private NodeService nodeService;

    @RequestMapping
    public String homeView(Model model) {
        fillProfile(model);
        List<Node> nodes = nodeService.getAllNodes();
        model.addAttribute("nodes", nodes);
        model.addAttribute("content", "home");
        return "master";
    }
}