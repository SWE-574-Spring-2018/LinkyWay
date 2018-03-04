/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.linkyway.Dao;

import com.linkyway.entity.AbstractEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author huseyin.kilic
 */
@Repository("base")
public class BaseDao<T extends AbstractEntity> {

  @PersistenceContext
  protected EntityManager em;

  public T persist(T entity) {
    return em.merge(entity);
  }

}
