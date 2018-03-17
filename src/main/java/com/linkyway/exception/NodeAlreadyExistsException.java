package com.linkyway.exception;

/**
 * @author huseyin.kilic
 */
public class NodeAlreadyExistsException extends Exception {

  public NodeAlreadyExistsException(Long existingNodeId) {
    super("Another node with the same type and name already exists! Existing node id: " + existingNodeId);
  }
}
