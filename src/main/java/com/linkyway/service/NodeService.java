/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.linkyway.service;

import com.linkyway.Dao.BaseDao;
import com.linkyway.domain.Node;
import com.linkyway.entity.NodeEntity;
import com.linkyway.mapper.NodeEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author huseyin.kilic
 */
@Service
public class NodeService {

  @Autowired
  private NodeEntityMapper entityMapper;

  @Autowired
  @Qualifier("node")
  private BaseDao<NodeEntity> dao;

  @Transactional
  public Node createNode(String name, String description) {
    Node node = new Node(name, description);
    NodeEntity entity = entityMapper.convert(node);
    return entityMapper.convert(dao.persist(entity));
  }
}
