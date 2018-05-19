package com.linkyway.controller.service;

import com.linkyway.model.domain.Node;
import com.linkyway.service.NodeService;
import com.linkyway.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;


/**
 * @author acersoz
 */

@Controller
@RequestMapping("/linkyway/api/tweet")
public class TweetController {
    @Autowired
    private NodeService nodeService;
    @Autowired
    private TweetService tweetService;

    @Autowired
    private Twitter twitter;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity searchTweet(@NotNull String url)
            throws org.springframework.social.ApiException {

      //  boolean isTweetConnected = false;
        //for (Node node : nodeService.getAllNodes()) {
       //     if (node.)
        //}

        Tweet searchResult = tweetService.searchTweets(url);
        return ResponseEntity.status(HttpStatus.OK).body(searchResult);
    }
}
