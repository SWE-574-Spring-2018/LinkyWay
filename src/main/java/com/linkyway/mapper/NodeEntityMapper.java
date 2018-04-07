package com.linkyway.mapper;

import com.linkyway.model.entity.Node;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huseyin.kilic
 */
@Component
public class NodeEntityMapper {

    public Node convert(com.linkyway.model.domain.Node domainObj) {
        Node entityObj = new Node();
        //entityObj.setId(domainObj.getId());
        entityObj.setName(domainObj.getName());
        entityObj.setDescription(domainObj.getDescription());
        entityObj.setType(domainObj.getType());
        return entityObj;
    }

    public com.linkyway.model.domain.Node convert(Node entityObj) {
        com.linkyway.model.domain.Node domainObj = new com.linkyway.model.domain.Node();
        domainObj.setId(entityObj.getId());
        domainObj.setName(entityObj.getName());
        domainObj.setDescription(entityObj.getDescription());
        domainObj.setType(entityObj.getType());
        return domainObj;
    }

    public List<com.linkyway.model.domain.Node> convert(List<Node> nodes) {
        List<com.linkyway.model.domain.Node> domainObjList = new ArrayList<>();
        for (Node entityObj : nodes) {
            domainObjList.add(convert(entityObj));
        }
        return domainObjList;
    }
}
