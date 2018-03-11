package com.linkyway.domain;

import lombok.Data;

/**
 * @author huseyin.kilic
 */
@Data
public class RelationshipType {

  private String code;
  private String name;
  private String description;

  public RelationshipType(String code, String name, String description) {
    this.code = code;
    this.name = name;
    this.description = description;
  }
}
