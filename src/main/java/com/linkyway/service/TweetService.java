package com.linkyway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author acersoz
 */

@Service
@Transactional
public class TweetService {

    @Autowired
    private Twitter twitter;

    public Tweet searchTweets(String url)
            throws org.springframework.social.ApiException {

        int index       = url.lastIndexOf("/");
        String sTweetId = url.substring(index + 1);
        Long tweetId = new Long(sTweetId);

         Tweet tweet = twitter.timelineOperations().getStatus(tweetId);//searchOperations().search(searchParameters);
        return tweet;
    }

}
