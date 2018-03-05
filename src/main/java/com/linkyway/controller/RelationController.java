package com.linkyway.controller;

import com.linkyway.domain.RelationType;
import com.linkyway.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;

import java.util.List;

/**
 * @author huseyin.kilic
 */
@Controller
@RequestMapping("/linkyway/relation")
public class RelationController {

  @Autowired
  private RelationService relationService;

  @RequestMapping
  @ResponseBody
  public ResponseEntity<List<RelationType>> searchRelationTypes(@RequestParam("keyword") String keyword,
          @RequestParam("language") String language) throws MediaWikiApiErrorException {
    List<RelationType> relationTypes = relationService.searchRelationTypes(keyword, language);
    if (CollectionUtils.isEmpty(relationTypes)) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
    return ResponseEntity.status(HttpStatus.OK).body(relationTypes);
  }
}
