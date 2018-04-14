package com.linkyway.model.exception;

/**
 * @author huseyin.kilic
 */
public class TweetDoesNotExistException extends Exception {

    public TweetDoesNotExistException(Long TweetId) {
        super("Tweet with id " + TweetId + " does not exist!");
    }
}
