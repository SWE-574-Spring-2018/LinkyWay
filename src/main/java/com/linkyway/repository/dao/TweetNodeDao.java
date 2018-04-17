/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.linkyway.repository.dao;

import com.linkyway.model.entity.TweetNode;
import com.linkyway.model.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author acersoz
 */
@Repository
public class TweetNodeDao extends BaseDao<TweetNode> {

    public List<TweetNode> findByNodeId(long nodeId) {
        Criteria criteria = ((Session) em.getDelegate()).createCriteria(TweetNode.class);
        criteria.add(Restrictions.eq("nodeId", nodeId));
        return criteria.list();
    }

}
