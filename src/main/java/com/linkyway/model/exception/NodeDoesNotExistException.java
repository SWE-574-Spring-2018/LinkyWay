package com.linkyway.model.exception;

/**
 * @author huseyin.kilic
 */
public class NodeDoesNotExistException extends Exception {

    public NodeDoesNotExistException(Long NodeId) {
        super("Node with id " + NodeId + " does not exist!");
    }
}
