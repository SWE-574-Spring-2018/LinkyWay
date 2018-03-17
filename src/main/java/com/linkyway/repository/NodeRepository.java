package com.linkyway.repository;

import com.linkyway.entity.Node;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.response.model.NodeModel;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huseyin.kilic
 */
@Repository
public class NodeRepository {

  @Autowired
  private Session session;

  public Node create(Node nodeEntity) {
    String query = "CREATE (node: " + nodeEntity.getType() + " {name: '" + nodeEntity.getName() + "', description: '"
            + nodeEntity.getDescription() + "' }) RETURN node";

    Result result = session.query(query, new HashMap<>());
    NodeModel nodeModel = (NodeModel)result.queryResults().iterator().next().get("node");
    Node newNode = new Node();
    newNode.setId(nodeModel.getId());
    newNode.setName(String.valueOf(nodeModel.getPropertyList().get(0).getValue()));
    newNode.setDescription(String.valueOf(nodeModel.getPropertyList().get(1).getValue()));
    newNode.setType(nodeModel.getLabels()[0]);
    return newNode;
  }

  public Node findByTypeAndName(String type, String name) {
    String query = "MATCH (node: " + type + "{name: '" + name + "'}) return node";
    Iterable<Map<String, Object>> resultList = session.query(query, new HashMap<>()).queryResults();
    if (!resultList.iterator().hasNext()) {
      return null;
    }
    NodeModel nodeModel = (NodeModel)resultList.iterator().next().get("node");
    Node foundNode = new Node();
    foundNode.setId(nodeModel.getId());
    foundNode.setName(String.valueOf(nodeModel.getPropertyList().get(0).getValue()));
    foundNode.setDescription(String.valueOf(nodeModel.getPropertyList().get(1).getValue()));
    foundNode.setType(nodeModel.getLabels()[0]);
    return foundNode;
  }

  public void delete(Long id) {
    String query = "MATCH (node) where id(node) = " + id + " detach delete node";
    session.query(query, new HashMap<>());
  }

  public Node findById(Long id) {
    String query = "MATCH (node) where id(node) = " + id + " return node";
    Iterable<Map<String, Object>> resultList = session.query(query, new HashMap<>()).queryResults();
    if (!resultList.iterator().hasNext()) {
      return null;
    }
    NodeModel nodeModel = (NodeModel)resultList.iterator().next().get("node");
    Node foundNode = new Node();
    foundNode.setId(nodeModel.getId());
    foundNode.setName(String.valueOf(nodeModel.getPropertyList().get(0).getValue()));
    foundNode.setDescription(String.valueOf(nodeModel.getPropertyList().get(1).getValue()));
    foundNode.setType(nodeModel.getLabels()[0]);
    return foundNode;
  }

}
