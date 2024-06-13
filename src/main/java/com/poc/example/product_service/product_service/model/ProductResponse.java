package com.poc.example.product_service.product_service.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductResponse {
    private Product product;
    private ProductPrice productPrice;
    private ProductReview productReview;
}
