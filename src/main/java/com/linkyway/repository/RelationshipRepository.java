package com.linkyway.repository;

import com.linkyway.entity.Relationship;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * @author huseyin.kilic
 */
@Repository
public class RelationshipRepository {

  @Autowired
  private Session session;

  public Relationship create(Relationship relationship) {
    String query = "MATCH (source) where id(source) = " + relationship.getSource().getId() + 
            " MATCH (target) where id(target) = " + relationship.getTarget().getId() +
            " CREATE (source)-[r:`" + relationship.getType() + "`]->(target)"
            + " return id(r)";

    Result resultList = session.query(query, new HashMap<>());
    Long relationshipId = ((Integer)resultList.iterator().next().get("id(r)")).longValue();
    Relationship newRelationship = new Relationship();
    newRelationship.setId(relationshipId);
    newRelationship.setSource(relationship.getSource());
    newRelationship.setTarget(relationship.getTarget());
    newRelationship.setType(relationship.getType());
    return newRelationship;
  }


  public void delete(Long id) {
    String query = "MATCH ()-[r]-() WHERE id(r)= " + id + " DELETE r";
    session.query(query, new HashMap<>());
  }

}
