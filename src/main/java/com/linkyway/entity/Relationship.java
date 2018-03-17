package com.linkyway.entity;

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
}
