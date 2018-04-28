package com.linkyway.service;

import com.linkyway.mapper.NodeEntityMapper;
import com.linkyway.model.domain.Link;
import com.linkyway.model.domain.RelationshipMap;
import com.linkyway.model.entity.Node;
import com.linkyway.model.entity.Relationship;
import com.linkyway.model.entity.TweetNode;
import com.linkyway.model.entity.User;
import com.linkyway.model.exception.NoMatchingNodeFoundException;
import com.linkyway.model.exception.NodeAlreadyExistsException;
import com.linkyway.model.exception.NodeDoesNotExistException;
import com.linkyway.model.exception.TweetDoesNotExistException;
import com.linkyway.repository.NodeRepository;
import com.linkyway.repository.dao.ProfileDao;
import com.linkyway.repository.dao.TweetNodeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author huseyin.kilic
 */
@Service
@Transactional
public class NodeService {

    @Autowired
    private NodeEntityMapper entityMapper;

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private Twitter twitter;

    @Autowired
    private TweetNodeDao tweetNodeDao;


    public com.linkyway.model.domain.Node getNodeByTypeAndName(String type, String name) throws NoMatchingNodeFoundException {
        Node foundNode = nodeRepository.findByTypeAndName(type, name);
        if (foundNode == null) {
            throw new NoMatchingNodeFoundException(type, name);
        }
        return entityMapper.convert(foundNode);
    }

    public List<com.linkyway.model.domain.Node> getAllNodes() {
        List<Node> nodes = nodeRepository.retrieveAll();
        return entityMapper.convert(nodes);
    }

    public com.linkyway.model.domain.Node createNode(com.linkyway.model.domain.Node node) throws NodeAlreadyExistsException {
        Node existingNode = nodeRepository.findByTypeAndName(node.getType(), node.getName());
        if (existingNode != null) {
            throw new NodeAlreadyExistsException(existingNode.getId());
        }

        Node entity = entityMapper.convert(node);
        return entityMapper.convert(nodeRepository.create(entity));
    }

    public void deleteNode(Long id) {
        nodeRepository.delete(id);
    }

    /**
     * @author acersoz
     */

    public com.linkyway.model.entity.TweetNode createTweetNode(com.linkyway.model.entity.TweetNode tweetNode)
            throws NodeDoesNotExistException, TweetDoesNotExistException {

        Node nodeCheck = nodeRepository.findById(tweetNode.getNodeId());

        if (nodeCheck == null) {
            throw new NodeDoesNotExistException(tweetNode.getNodeId());
        }

        Tweet tweet = twitter.timelineOperations().getStatus(tweetNode.getTweetId());

        if (tweet == null) {
            throw new TweetDoesNotExistException(tweetNode.getTweetId());
        }

        try {
            tweetNodeDao.persist(tweetNode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tweetNode;
    }

    public List<Tweet> getConnectedTweets(Long nodeId) throws NodeDoesNotExistException {

        Node nodeCheck = nodeRepository.findById(nodeId);

        if (nodeCheck == null) {
            throw new NodeDoesNotExistException(nodeId);
        }

        List<TweetNode> tweetNodes = tweetNodeDao.findByNodeId(nodeId);

        List<Tweet> tweets = new ArrayList<>();

        for (int i = 0; i < tweetNodes.size(); i++) {

            TweetNode tweetNode = tweetNodes.get(i);
            Long tweetId = tweetNode.getTweetId();
            Tweet tweet = twitter.timelineOperations().getStatus(tweetId);
            tweets.add(tweet);
        }

        return tweets;
    }

    public RelationshipMap getRelationshipMap(Long nodeId) throws NodeDoesNotExistException {

        Node nodeCheck = nodeRepository.findById(nodeId);

        if (nodeCheck == null) {
            throw new NodeDoesNotExistException(nodeId);
        }

        List<Relationship> relationships = nodeRepository.findRelatedNodes(nodeId);
        List<Node> nodeList = new ArrayList<>();
        List<Link> linkList = new ArrayList<>();

        for (int i = 0; i < relationships.size(); i++) {

            Relationship relationship = relationships.get(i);

            if (!nodeList.contains(relationship.getTarget())){
                nodeList.add(relationship.getTarget());
            }

            if (!nodeList.contains(relationship.getSource())){
                nodeList.add(relationship.getSource());
            }

            Link link = new Link();
            link.setSource(relationship.getSource().getId());
            link.setTarget(relationship.getTarget().getId());
            link.setType(relationship.getType());
            linkList.add(link);
        }


        RelationshipMap relationshipMap = new RelationshipMap();
        relationshipMap.setLinkList(linkList);
        relationshipMap.setNodeList(nodeList);



        return relationshipMap;
    }
/*
    //salih
    public RelationshipMap getRelationshipMap(long nodeId) throws NodeDoesNotExistException {
        List<Relationship> relationshipList = getRelatedNodes(nodeId);

        List<Node> nodeList = new ArrayList<>();
        List<Link> linkList = new ArrayList<>();
        for (Relationship relationship : relationshipList) {
            Node sourceNode = relationship.getSource();
            Node targetNode = relationship.getSource();

            if (!nodeList.contains(sourceNode))
                nodeList.add(sourceNode);

            if (!nodeList.contains(targetNode))
                nodeList.add(targetNode);

            Link link = new Link(sourceNode.getId(), targetNode.getId(), relationship.getType());
            linkList.add(link);
        }
        return new RelationshipMap(nodeList, linkList);
    }

    public class RelationshipMap {
        private List<Node> nodeList;
        private List<Link> linkList;

        RelationshipMap(List<Node> nodeList, List<Link> linkList) {
            this.nodeList = nodeList;
            this.linkList = linkList;
        }
    }

    private class Link {
        private long source;
        private long target;
        private String type;

        Link(long source, long target, String type) {
            this.source = source;
            this.target = target;
            this.type = type;
        }
    }
    */
    //salih
}
