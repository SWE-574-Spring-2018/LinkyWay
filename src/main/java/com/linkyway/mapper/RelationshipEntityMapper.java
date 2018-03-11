package com.linkyway.mapper;

import com.linkyway.entity.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author huseyin.kilic
 */
@Component
public class RelationshipEntityMapper {

  @Autowired
  private NodeEntityMapper nodeEntityMapper;

  public Relationship convert(com.linkyway.domain.Relationship domainObj) {
    Relationship entityObj = new Relationship();
    entityObj.setId(domainObj.getId());
    entityObj.setSource(nodeEntityMapper.convert(domainObj.getSource()));
    entityObj.setTarget(nodeEntityMapper.convert(domainObj.getTarget()));
    entityObj.setType(domainObj.getType());
    return entityObj;
  }

  public com.linkyway.domain.Relationship convert(Relationship entityObj) {
    com.linkyway.domain.Relationship domainObj = new com.linkyway.domain.Relationship();
    domainObj.setId(entityObj.getId());
    domainObj.setSource(nodeEntityMapper.convert(entityObj.getSource()));
    domainObj.setTarget(nodeEntityMapper.convert(entityObj.getTarget()));
    domainObj.setType(entityObj.getType());
    return domainObj;
  }

}
