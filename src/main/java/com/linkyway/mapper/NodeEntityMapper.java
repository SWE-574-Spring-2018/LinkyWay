package com.linkyway.mapper;

import com.linkyway.entity.Node;
import org.springframework.stereotype.Component;

/**
 * @author huseyin.kilic
 */
@Component
public class NodeEntityMapper {

  public Node convert(com.linkyway.domain.Node domainObj) {
    Node entityObj = new Node();
    //entityObj.setId(domainObj.getId());
    entityObj.setName(domainObj.getName());
    entityObj.setDescription(domainObj.getDescription());
    entityObj.setType(domainObj.getType());
    return entityObj;
  }

  public com.linkyway.domain.Node convert(Node entityObj) {
    com.linkyway.domain.Node domainObj = new com.linkyway.domain.Node();
    domainObj.setId(entityObj.getId());
    domainObj.setName(entityObj.getName());
    domainObj.setDescription(entityObj.getDescription());
    domainObj.setType(entityObj.getType());
    return domainObj;
  }
}
