package com.linkyway.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author huseyin.kilic
 */
@Data
@NoArgsConstructor
public class Node {

  private long id;

  @NotNull
  private String name;

  @NotNull
  private String description;

  @NotNull
  private String type;

  public Node(String type, String name, String description) {
    this.type = type;
    this.name = name;
    this.description = description;
  }

}
