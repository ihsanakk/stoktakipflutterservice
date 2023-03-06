package com.tempter.stoktakipservice.rest.dto;

import lombok.Data;

@Data
public class ProductDTO {
    
    private String barcode;
    private String name;
    private String category;
    private Integer quantity;
    private Double price;
}
