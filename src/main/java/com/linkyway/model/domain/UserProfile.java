/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.linkyway.model.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.social.twitter.api.TwitterProfile;

/**
 * @author huseyin.kilic
 */
@Data
@Builder
public class UserProfile {

    private Long id;
    private long twitterId;
    private String name;
    private TwitterProfile twitterProfile;
    private String profileImageUrl;

}
