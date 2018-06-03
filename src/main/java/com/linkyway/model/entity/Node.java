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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

