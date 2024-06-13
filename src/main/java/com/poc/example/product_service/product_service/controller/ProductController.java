package com.poc.example.product_service.product_service.controller;

import com.poc.example.product_service.product_service.model.ProductResponse;
import com.poc.example.product_service.product_service.service.PriceHandlerService;
import com.poc.example.product_service.product_service.service.ProductService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private PriceHandlerService priceHandlerService;

    @GetMapping("api/v1/product/{id}")
    public Mono<ProductResponse> getProductDetail(@PathVariable("id") String id) {
        return service.getProductDetails(id);
    }


//    //@GetMapping("api/v1/priceCB")
//    //public Mono<String> test() {
//        return priceHandlerService.getProductPrice("66684bd24801491a9f837a99");
//    }
}
