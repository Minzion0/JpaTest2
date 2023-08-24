package com.green.jap1.product.model;

import lombok.Data;

@Data
public class ProductRegDto {
    private String name;
    private int price;
    private int stock;
    private Long providerId;
    private String description;
}
