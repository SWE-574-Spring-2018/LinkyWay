package com.linkyway.service;

import com.linkyway.model.entity.WikidataItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wikidata.wdtk.wikibaseapi.WbGetEntitiesSearchData;
import org.wikidata.wdtk.wikibaseapi.WbSearchEntitiesResult;
import org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher;
import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class WikidataItemService {
    private static final String TYPE = "item";


    public List<WikidataItem> searchWikidataItems(String keyword, String language)
            throws MediaWikiApiErrorException {
        WikibaseDataFetcher wbdf = WikibaseDataFetcher.getWikidataDataFetcher();

        WbGetEntitiesSearchData criteria = new WbGetEntitiesSearchData();
        criteria.type = TYPE;
        criteria.search = keyword;
        criteria.language = language;
        List<WbSearchEntitiesResult> searchResults = wbdf.searchEntities(criteria);

        List<WikidataItem> results = new ArrayList<>();
        searchResults.stream().forEach(searchResult -> results
                .add(new WikidataItem(searchResult.getTitle(), searchResult.getLabel(), searchResult.getDescription())));
        return results;
    }
}
