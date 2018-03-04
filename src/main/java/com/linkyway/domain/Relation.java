/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.linkyway.domain;

import lombok.Data;

/**
 * @author huseyin.kilic
 */
@Data
public class Relation {
  private Node origin;
  private Node destination;
  private RelationType relationType;
}
