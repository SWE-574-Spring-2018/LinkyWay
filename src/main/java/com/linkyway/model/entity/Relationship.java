package com.linkyway.model.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.StartNode;

import javax.persistence.GeneratedValue;

/**
 * @author huseyin.kilic
 */
@Data
public class Relationship extends AbstractEntity {

    @GraphId
    @GeneratedValue
    private Long id;

    @StartNode
    private Node source;

    @EndNode
    private Node target;

    @Property
    private String type;

    public Relationship(Node sourceNode, Node targetNode, String type) {
        this.setSource(sourceNode);
        this.setTarget(targetNode);
        this.setType(type);
    }

    public Relationship() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Node getSource() {
        return source;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    public Node getTarget() {
        return target;
    }

    public void setTarget(Node target) {
        this.target = target;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
