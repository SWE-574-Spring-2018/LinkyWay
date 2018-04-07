package com.linkyway.model.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author huseyin.kilic
 */
@Data
public class Relationship {

    private Long id;

    @NotNull
    private Node source;

    @NotNull
    private Node target;

    @NotNull
    private String type;

    public Relationship(Node sourceNode, Node targetNode, String relationshipType) {
        this.source = sourceNode;
        this.target = targetNode;
        this.type = relationshipType;
    }

    public Relationship() {
    }
}
