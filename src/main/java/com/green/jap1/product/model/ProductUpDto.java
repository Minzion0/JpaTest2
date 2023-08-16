package com.green.jap1.product.model;

import lombok.Data;

@Data
public class ProductUpDto {
    private long number;
    private String  name;
    private Integer price;
    private Integer stock;
}
