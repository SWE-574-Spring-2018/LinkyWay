package com.linkyway.model.domain;

import com.linkyway.model.domain.Link;
import com.linkyway.model.entity.Node;
import lombok.Data;

import java.util.List;

@Data
public class RelationshipMap {
    private List<Node> nodeList;
    private List<Link> linkList;

}
