package com.poc.example.product_service.product_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ProductPrice {

    @JsonIgnore
    private String id;
    private String price;
}
