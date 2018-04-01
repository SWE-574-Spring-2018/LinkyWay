package com.linkyway.controller;

import com.linkyway.domain.RelationshipType;
import com.linkyway.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.twitter.api.*;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestOperations;
import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;

import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.util.List;


/**
 * @author acersoz
 */

@Controller
@RequestMapping("/linkyway/api/tweet")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @Autowired
    private Twitter twitter;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity searchTweet(@NotNull String url)
            throws org.springframework.social.ApiException {

        SearchResults searchResults = tweetService.searchTweets(url);
        return ResponseEntity.status(HttpStatus.OK).body(searchResults);
    }
}
