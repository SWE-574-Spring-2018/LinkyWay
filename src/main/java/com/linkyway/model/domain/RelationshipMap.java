package com.linkyway.model.domain;

import com.linkyway.model.entity.Node;
import lombok.Data;

import java.util.List;

@Data
public class RelationshipMap {
    private List<Node> nodeList;
    private List<Link> linkList;

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<Link> linkList) {
        this.linkList = linkList;
    }
}
