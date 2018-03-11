package com.linkyway.exception;

/**
 * @author huseyin.kilic
 */
public class NoMatchingNodeFoundException extends Exception {

  public NoMatchingNodeFoundException(String type, String name) {
    super("No matching node found with type: " + type + " and name: " + name);
  }

  public NoMatchingNodeFoundException(Long id) {
    super("No matching node found with id: " + id);
  }
}
