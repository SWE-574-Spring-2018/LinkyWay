package com.linkyway.model.exception;

/**
 * @author acersoz
 */
public class NodeDoesNotExistException extends Exception {

    public NodeDoesNotExistException(Long NodeId) {
        super("Node with id " + NodeId + " does not exist!");
    }
}
