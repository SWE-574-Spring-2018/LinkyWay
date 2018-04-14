package com.linkyway.model.entity;

public class WikidataItem {

    private String itemId;
    private String name;
    private String description;

    public WikidataItem(String itemId, String name, String description) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
    }
}