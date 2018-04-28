package com.linkyway.repository;

import com.linkyway.model.entity.Node;
import com.linkyway.model.entity.Relationship;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.response.model.NodeModel;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Repository;

import javax.management.relation.Relation;
import java.util.*;

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
        NodeModel nodeModel = (NodeModel) result.queryResults().iterator().next().get("node");
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
        NodeModel nodeModel = (NodeModel) resultList.iterator().next().get("node");
        Node foundNode = new Node();
        foundNode.setId(nodeModel.getId());
        foundNode.setName(String.valueOf(nodeModel.getPropertyList().get(0).getValue()));
        foundNode.setDescription(String.valueOf(nodeModel.getPropertyList().get(1).getValue()));
        foundNode.setType(nodeModel.getLabels()[0]);
        return foundNode;
    }

    public List<Node> retrieveAll() {
        List<Node> nodes = new ArrayList<>();
        String query = "MATCH (node) return node";
        Iterable<Map<String, Object>> resultList = session.query(query, new HashMap<>()).queryResults();

        for (Object result : resultList) {
            NodeModel nodeModel = (NodeModel) (((HashMap) result).get("node"));
            Node nextNode = new Node();
            nextNode.setId(nodeModel.getId());
            nextNode.setName(String.valueOf(nodeModel.getPropertyList().get(0).getValue()));
            nextNode.setDescription(String.valueOf(nodeModel.getPropertyList().get(1).getValue()));
            nextNode.setType(nodeModel.getLabels()[0]);
            nodes.add(nextNode);
        }
        return nodes;
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
        NodeModel nodeModel = (NodeModel) resultList.iterator().next().get("node");
        Node foundNode = new Node();
        foundNode.setId(nodeModel.getId());
        foundNode.setName(String.valueOf(nodeModel.getPropertyList().get(0).getValue()));
        foundNode.setDescription(String.valueOf(nodeModel.getPropertyList().get(1).getValue()));
        foundNode.setType(nodeModel.getLabels()[0]);
        return foundNode;
    }

    /**
     * @author acersoz
     */

    public List<Relationship> findRelatedNodes(Long id) {
        String query = "MATCH (sourceNode)-[r]->(targetNode) WHERE id(sourceNode)=" + id +
                " RETURN sourceNode,type(r) as relationType,targetNode";
        Iterable<Map<String, Object>> resultList = session.query(query, new HashMap<>()).queryResults();
        if (!resultList.iterator().hasNext()) {
            return null;
        }

        List<Relationship> relations = new ArrayList<>();
        Iterator<Map<String, Object>> iterator = resultList.iterator();

        while (iterator.hasNext()){

            Map<String, Object> objectMap = iterator.next();

            NodeModel sourceNodeModel = (NodeModel) objectMap.get("sourceNode");
            NodeModel targetNodeModel = (NodeModel) objectMap.get("targetNode");
            String relationType       = (String)    objectMap.get("relationType"); //"x";

            Node sourceNode = new Node();
            Node targetNode = new Node();

            sourceNode.setId(sourceNodeModel.getId());
            sourceNode.setName(String.valueOf(sourceNodeModel.getPropertyList().get(0).getValue()));
            sourceNode.setDescription(String.valueOf(sourceNodeModel.getPropertyList().get(1).getValue()));
            sourceNode.setType(sourceNodeModel.getLabels()[0]);

            targetNode.setId(targetNodeModel.getId());
            targetNode.setName(String.valueOf(targetNodeModel.getPropertyList().get(0).getValue()));
            targetNode.setDescription(String.valueOf(targetNodeModel.getPropertyList().get(1).getValue()));
            targetNode.setType(targetNodeModel.getLabels()[0]);

            Relationship relationship = new Relationship();
            relationship.setSource(sourceNode);
            relationship.setTarget(targetNode);
            relationship.setType(relationType);

            relations.add(relationship);

        }
        return relations;
    }

}
