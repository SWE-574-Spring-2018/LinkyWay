package com.linkyway.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    public Long getId() {
        return id;
    }

    public long getTweetId() {
        return tweetId;
    }

    public long getNodeId() {
        return nodeId;
    }
}
