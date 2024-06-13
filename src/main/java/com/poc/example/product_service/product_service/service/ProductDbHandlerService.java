package com.poc.example.product_service.product_service.service;

import com.poc.example.product_service.product_service.model.Product;
import com.poc.example.product_service.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;

@Service
public class ProductDbHandlerService {

    @Autowired
    private ProductRepository productRepository;

    public Mono<Product> getProductDbDetail(String productId) {
        return productRepository.findById(productId);
    }
}
