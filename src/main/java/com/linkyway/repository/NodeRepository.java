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
        String query = "MATCH (sourceNode)-[r]-(targetNode)  WHERE id(sourceNode)=" + id +
                " RETURN sourceNode,type(r) as relationType, targetNode  UNION ALL MATCH  (sourceNode)-[r]-(targetNode), (targetNode)-[r2]-(targetNode2)  " +
                "WHERE id(sourceNode)= " + id + " AND id(targetNode2) <> id(sourceNode)  RETURN targetNode as sourceNode, type(r2) as relationType,targetNode2 as targetNode";
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

    public void CreateTestDB() {
        String query1 = "MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r";
        session.query(query1, new HashMap<>());
        String query2 = "CREATE (n1:Person { name: \"Donald Trump\", description: \"US President\" }) \n" +
                "CREATE (n2:Person { name: \"Stormy Daniels\", description: \"Pornstar\" })\n" +
                "CREATE (n3:Person { name: \"Michael Cohen\", description: \"Lawyer of Donald Trump\" })\n" +
                "CREATE (n4:Cooperation { name: \"Facebook Inc\", description: \"Social Media Behemoth\" })\n" +
                "CREATE (n5:Event { name: \"United States Presidential Election, 2016\", description: \" \" })\n" +
                "CREATE (n6:Event { name: \"Facebook-Cambridge Analytica Data Scandal\", description: \" \" })\n" +
                "CREATE (n7:Person { name: \"Mark Zuckerberg\", description: \"CEO and founder of Facebook Inc.\" })\n" +
                "CREATE (n8:Organization { name: \"Republicans\", description: \"Political Party in US\" })";
        session.query(query2, new HashMap<>());
        String query3 = "MATCH (n1), (n2), (n3), (n4), (n5), (n6), (n7), (n8) WHERE n1.name = \"Donald Trump\" AND n2.name = \"Stormy Daniels\" AND n3.name = \"Michael Cohen\" AND n4.name = \"Facebook Inc\" AND n5.name = \"United States Presidential Election, 2016\" AND  n6.name = \"Facebook-Cambridge Analytica Data Scandal\" AND n7.name = \"Mark Zuckerberg\" AND n8.name = \"Republicans\" CREATE (n4)-[r1:FOUNDED_BY]->(n7), (n1)-[r2:ELECTED_IN]->(n5), (n3)-[r3:REPRESENTS]->(n1), (n3)-[r4:BLACKMAILED]->(n2), (n1)-[r5:HAD_AFFAIR_WITH]->(n2), (n4)-[r6:PART_OF]->(n6), (n1)-[r7:WINNER]->(n5), (n3)-[r8:MEMBER_OF_POLITICAL_PARTY]->(n8), (n1)-[r9:MEMBER_OF_POLITICAL_PARTY]->(n8), (n6)-[r10:HAS_PART]->(n5)";
        session.query(query3, new HashMap<>());
    }
}
