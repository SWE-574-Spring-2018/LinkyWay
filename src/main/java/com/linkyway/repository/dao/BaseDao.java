/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.linkyway.repository.dao;

import com.linkyway.model.entity.AbstractEntity;
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

    public void persist(T entity) {
        em.merge(entity);
    }

}
