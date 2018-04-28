package com.linkyway.controller.service;

import com.linkyway.model.domain.Node;
import com.linkyway.model.entity.TweetNode;
import com.linkyway.model.exception.NoMatchingNodeFoundException;
import com.linkyway.model.exception.NodeAlreadyExistsException;
import com.linkyway.model.exception.NodeDoesNotExistException;
import com.linkyway.model.exception.TweetDoesNotExistException;
import com.linkyway.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author huseyin.kilic
 */
@Controller
@RequestMapping("/linkyway/api/node")
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createNode(@Valid @ModelAttribute Node node) {
        if (node == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }
        try {
            Node newNode = nodeService.createNode(node);
            return ResponseEntity.status(HttpStatus.CREATED).body(newNode);
        } catch (NodeAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getNodeByTypeAndName(@NotNull String type, @NotNull String name) {
        try {
            Node foundNode = nodeService.getNodeByTypeAndName(type, name);
            return ResponseEntity.status(HttpStatus.OK).body(foundNode);
        } catch (NoMatchingNodeFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteNode(@NotNull Long id) {
        nodeService.deleteNode(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


    /**
     * @author acersoz
     */

    @RequestMapping(path = "/tweet", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createTweetNode(@Valid @ModelAttribute TweetNode tweetNode) {
        if (tweetNode == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tweetNode);
        }

        try {
            TweetNode newTweetNode = nodeService.createTweetNode(tweetNode);
            return ResponseEntity.status(HttpStatus.CREATED).body(newTweetNode);
        }

        catch (NodeDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        catch (TweetDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


    @RequestMapping(method = RequestMethod.GET, path = "/{nodeId}/tweets")
    @ResponseBody
    public ResponseEntity getConnectedTweets(@PathVariable("nodeId") Long nodeId) {
        try {
            List<Tweet> connectedTweets = nodeService.getConnectedTweets(nodeId);
            return ResponseEntity.status(HttpStatus.OK).body(connectedTweets);
        } catch (NodeDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @RequestMapping(method = RequestMethod.GET, path = "/{nodeId}/related")
    @ResponseBody
    public ResponseEntity getConnectedNodes(@PathVariable("nodeId") Long nodeId) {
        try {
            List<Tweet> connectedTweets = nodeService.getRelatedNodes(nodeId);
            return ResponseEntity.status(HttpStatus.OK).body(connectedTweets);
        } catch (NodeDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
