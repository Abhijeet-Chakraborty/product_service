package com.poc.example.product_service.product_service.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document(collection = "product")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
}
