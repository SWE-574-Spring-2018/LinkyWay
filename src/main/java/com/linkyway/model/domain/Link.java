package com.linkyway.model.domain;

import lombok.Data;

@Data
public class Link {
    private Long source;
    private Long target;
    private String type;
}
