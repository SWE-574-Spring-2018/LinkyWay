package com.linkyway.controller.service;

import com.linkyway.model.entity.WikidataItem;
import com.linkyway.service.WikidataItemService;
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

@Controller
@RequestMapping(method = RequestMethod.GET, path = "/linkyway/api/wikidata_item")
public class WikidataItemController {

    @Autowired
    private WikidataItemService wikidataItemService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity searchWikidataItem(@NotNull String keyword, @NotNull String language)
            throws MediaWikiApiErrorException {

        List<WikidataItem> wikidataItems = wikidataItemService.searchWikidataItems(keyword, language);
        if (CollectionUtils.isEmpty(wikidataItems)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(wikidataItems);
    }
}
