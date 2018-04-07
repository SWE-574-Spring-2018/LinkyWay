package com.linkyway.controller.service;

import com.linkyway.model.domain.Node;
import com.linkyway.model.exception.NoMatchingNodeFoundException;
import com.linkyway.model.exception.NodeAlreadyExistsException;
import com.linkyway.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author huseyin.kilic
 */
@Controller
@RequestMapping("/linkyway/api/node")
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createNode(@Valid @ModelAttribute Node node) {
        if (node == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }
        try {
            Node newNode = nodeService.createNode(node);
            return ResponseEntity.status(HttpStatus.CREATED).body(newNode);
        } catch (NodeAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getNodeByTypeAndName(@NotNull String type, @NotNull String name) {
        try {
            Node foundNode = nodeService.getNodeByTypeAndName(type, name);
            return ResponseEntity.status(HttpStatus.OK).body(foundNode);
        } catch (NoMatchingNodeFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteNode(@NotNull Long id) {
        nodeService.deleteNode(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
