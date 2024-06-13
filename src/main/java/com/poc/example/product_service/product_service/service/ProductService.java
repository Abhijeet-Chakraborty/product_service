package com.poc.example.product_service.product_service.service;

import com.poc.example.product_service.product_service.model.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductDbHandlerService productDbHandlerService;
    @Autowired
    private PriceHandlerService priceHandlerService;
    @Autowired
    private ReviewHandlerService reviewHandlerService;
    public Mono<ProductResponse> getProductDetails(String productId) {
        var price = priceHandlerService.getProductPrice(productId);
        var review = reviewHandlerService.getProductReview(productId);
        var dbHandler = productDbHandlerService.getProductDbDetail(productId);

        return Mono.zip(price, review, dbHandler)
                .map(tuple-> {
                    return ProductResponse.builder()
                            .productPrice(tuple.getT1())
                            .productReview(tuple.getT2())
                            .product(tuple.getT3())
                            .build();
                });
    }
}
