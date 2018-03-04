/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.linkyway.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author huseyin.kilic
 */
@Data
@Entity
@Table(name = "Node")
public class NodeEntity extends AbstractEntity {

  @Id
  @GeneratedValue
  private long id;
  private String name;
  private String description;

}
