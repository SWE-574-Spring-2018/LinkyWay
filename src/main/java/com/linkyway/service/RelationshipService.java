package com.linkyway.service;

import com.linkyway.mapper.RelationshipEntityMapper;
import com.linkyway.model.domain.RelationshipType;
import com.linkyway.model.entity.Node;
import com.linkyway.model.entity.Relationship;
import com.linkyway.model.exception.NoMatchingNodeFoundException;
import com.linkyway.repository.NodeRepository;
import com.linkyway.repository.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
public class RelationshipService {

    private static final String TYPE = "property";

    @Autowired
    private RelationshipEntityMapper entityMapper;

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private RelationshipRepository relationshipRepository;

    public List<RelationshipType> searchRelationshipTypes(String keyword, String language)
            throws MediaWikiApiErrorException {
        WikibaseDataFetcher wbdf = WikibaseDataFetcher.getWikidataDataFetcher();

        WbGetEntitiesSearchData criteria = new WbGetEntitiesSearchData();
        criteria.type = TYPE;
        criteria.search = keyword;
        criteria.language = language;
        List<WbSearchEntitiesResult> searchResults = wbdf.searchEntities(criteria);

        List<RelationshipType> results = new ArrayList<>();
        searchResults.stream().forEach(searchResult -> results
                .add(new RelationshipType(searchResult.getTitle(), searchResult.getLabel(), searchResult.getDescription())));
        return results;
    }


    public com.linkyway.model.domain.Relationship createRelation(Long sourceNodeId, Long targetNodeId, String relationshipType)
            throws NoMatchingNodeFoundException {
        Node sourceNode = nodeRepository.findById(sourceNodeId);
        if (sourceNode == null) {
            throw new NoMatchingNodeFoundException(sourceNodeId);
        }
        Node targetNode = nodeRepository.findById(targetNodeId);
        if (targetNode == null) {
            throw new NoMatchingNodeFoundException(sourceNodeId);
        }

        Relationship relationshipEntity = new Relationship(sourceNode, targetNode, relationshipType);
        Relationship newRelationship = relationshipRepository.create(relationshipEntity);
        return entityMapper.convert(newRelationship);
    }

}
