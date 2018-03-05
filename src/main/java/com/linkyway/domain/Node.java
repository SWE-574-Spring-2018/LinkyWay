package com.linkyway.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

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

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<Relation> relations;

  public Node(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
