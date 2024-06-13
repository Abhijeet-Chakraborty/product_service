package com.poc.example.product_service.product_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.example.product_service.product_service.model.Product;
import com.poc.example.product_service.product_service.model.ProductPrice;
import com.poc.example.product_service.product_service.model.ProductReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Service
public class ReviewHandlerService {

    private final WebClient webClient;

    public ReviewHandlerService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8888").build();
    }

    public Mono<ProductReview> getProductReview(String productId) {
        return webClient.get()
                .uri("/api/product-review/" + productId)
                .retrieve()
                .bodyToMono(String.class)
                .map(json -> {
                    ObjectMapper mapper = new ObjectMapper();
                    ProductReview productReview = null;
                    try {
                        productReview  = mapper.readValue(json, ProductReview.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    return productReview;
                });
    }
}
