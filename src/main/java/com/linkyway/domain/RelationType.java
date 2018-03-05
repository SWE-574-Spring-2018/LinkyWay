package com.linkyway.domain;

import lombok.Data;

/**
 * @author huseyin.kilic
 */
@Data
public class RelationType {
  private String title;
  private String description;

  public RelationType(String title, String description) {
    this.title = title;
    this.description = description;
  }
}
