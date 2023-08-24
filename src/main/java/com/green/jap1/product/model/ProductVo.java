package com.green.jap1.product.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductVo {
    private long number;
    private String name;
    private int price;
    private int stock;
    private String providerName;
    private String description;
}
