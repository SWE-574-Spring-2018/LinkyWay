/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.linkyway.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.social.twitter.api.TwitterProfile;

import java.util.List;

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
