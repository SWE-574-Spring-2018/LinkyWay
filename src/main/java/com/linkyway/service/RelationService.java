/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.linkyway.service;

import com.linkyway.domain.RelationType;
import org.springframework.stereotype.Service;
import org.wikidata.wdtk.wikibaseapi.WbGetEntitiesSearchData;
import org.wikidata.wdtk.wikibaseapi.WbSearchEntitiesResult;
import org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher;
import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huseyin.kilic
 */
@Service
public class RelationService {

  private static final String TYPE = "property";


  public List<RelationType> searchRelationTypes(String keyword, String language) throws MediaWikiApiErrorException {
    WikibaseDataFetcher wbdf = WikibaseDataFetcher.getWikidataDataFetcher();

    WbGetEntitiesSearchData criteria = new WbGetEntitiesSearchData();
    criteria.type = TYPE;
    criteria.search = keyword;
    criteria.language = language;
    List<WbSearchEntitiesResult> searchResults = wbdf.searchEntities(criteria);

    List<RelationType> results = new ArrayList<>();
    for (WbSearchEntitiesResult searchResult : searchResults) {
      results.add(new RelationType(searchResult.getTitle(), searchResult.getDescription()));
    }

    return results;
  }
}
