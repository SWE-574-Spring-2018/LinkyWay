package com.linkyway.controller.service;

import com.linkyway.model.domain.Relationship;
import com.linkyway.model.domain.RelationshipType;
import com.linkyway.model.exception.NoMatchingNodeFoundException;
import com.linkyway.service.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author huseyin.kilic
 */
@Controller
@RequestMapping("/linkyway/api/relation")
public class RelationshipController {

    @Autowired
    private RelationshipService relationshipService;

    @RequestMapping(path = "/search", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity searchRelationshipTypes(@NotNull String keyword, @NotNull String language)
            throws MediaWikiApiErrorException {
        List<RelationshipType> relationshipTypes = relationshipService.searchRelationshipTypes(keyword, language);
        if (CollectionUtils.isEmpty(relationshipTypes)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(relationshipTypes);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createRelation(@NotNull Long sourceNodeId, @NotNull Long targetNodeId,
                                         @NotNull String relationshipType) {
        try {
            Relationship newRelationship = relationshipService.createRelation(sourceNodeId, targetNodeId, relationshipType);
            return ResponseEntity.status(HttpStatus.CREATED).body(newRelationship);
        } catch (NoMatchingNodeFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
