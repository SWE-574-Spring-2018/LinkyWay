/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.linkyway.mapper;

import com.linkyway.domain.Node;
import com.linkyway.entity.NodeEntity;
import org.springframework.stereotype.Component;

/**
 * @author huseyin.kilic
 */
@Component
public class NodeEntityMapper {

  public NodeEntity convert(Node node) {
    NodeEntity entity = new NodeEntity();
    entity.setName(node.getName());
    entity.setDescription(node.getDescription());
    return entity;
  }

  public Node convert(NodeEntity entity) {
    Node node = new Node();
    node.setId(entity.getId());
    node.setName(entity.getName());
    node.setDescription(entity.getDescription());
    return node;
  }
}
