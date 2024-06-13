package com.poc.example.product_service.product_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ProductReview {

    @JsonIgnore
    private String id;
    private String review;
}
