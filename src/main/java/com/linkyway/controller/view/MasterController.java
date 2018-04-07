/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.linkyway.controller.view;

import com.linkyway.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huseyin.kilic
 */
@Controller
@RequestMapping("/")
public class MasterController extends BaseController {
    @Autowired
    private ConnectionRepository connectionRepository;

    @RequestMapping("/")
    public String masterView() {
        boolean isTwitterAccountEmpty = CollectionUtils.isEmpty(connectionRepository.findConnections(Twitter.class));
        boolean isConnectionRepositoryEmpty = connectionRepository.findPrimaryConnection(Twitter.class) == null;
        boolean isUserLoggedIn = !(isTwitterAccountEmpty || isConnectionRepositoryEmpty);

        String targetView;
        if (isUserLoggedIn)
            targetView = "home";
        else
            targetView = "login";

        return "redirect:/" + targetView;
    }
}