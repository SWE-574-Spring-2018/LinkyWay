package com.linkyway.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.GeneratedValue;

/**
 * @author huseyin.kilic
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NodeEntity
public class Node extends AbstractEntity {
    @GraphId
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private String description;
}