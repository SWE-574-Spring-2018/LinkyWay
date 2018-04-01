package com.linkyway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TweetService {

    @Autowired
    private Twitter twitter;

    public SearchResults searchTweets(String url)
            throws org.springframework.social.ApiException {

        int index       = url.lastIndexOf("/");
        String sTweetId = url.substring(index + 1);
        Long tweetId = new Long(sTweetId);
        Long sinceId = tweetId - 1;
        Long maxId   = tweetId + 1;

        SearchParameters searchParameters = new SearchParameters("a");
        searchParameters.sinceId(sinceId);
        searchParameters.maxId(maxId);
        SearchResults searchResults = twitter.searchOperations().search(searchParameters);
        return searchResults;
    }

}
