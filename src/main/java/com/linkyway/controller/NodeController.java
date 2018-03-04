/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.linkyway.controller;

import com.linkyway.domain.Node;
import com.linkyway.domain.RelationType;
import com.linkyway.service.NodeService;
import com.linkyway.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;

import javax.validation.Valid;
import java.util.List;

/**
 * @author huseyin.kilic
 */
@Controller
@RequestMapping("/linkyway/node")
public class NodeController {

  @Autowired
  private NodeService nodeService;

  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Node> createNode(@Valid @ModelAttribute Node node) {
    if (node == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
    }
    Node newNode = nodeService.createNode(node.getName(), node.getDescription());
    return ResponseEntity.status(HttpStatus.CREATED).body(newNode);
  }
}
