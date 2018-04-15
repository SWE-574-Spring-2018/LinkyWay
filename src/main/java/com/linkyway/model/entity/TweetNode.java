package com.linkyway.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author acersoz
 */

@Entity
@Data
public class TweetNode extends AbstractEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private long tweetId;
    private long nodeId;
}
