package com.poc.example.product_service.product_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.example.product_service.product_service.model.Product;
import com.poc.example.product_service.product_service.model.ProductPrice;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Service
public class PriceHandlerService {
    private final WebClient webClient;

    public PriceHandlerService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8888").build();
    }


    @CircuitBreaker(name = "productService", fallbackMethod = "priceFallBackMethod")
    public Mono<ProductPrice> getProductPrice(String productId) {
        return webClient.get()
                .uri("/api/product-price/" + productId)
                .retrieve()
                .bodyToMono(String.class)
                .map(json -> {
                    ObjectMapper mapper = new ObjectMapper();
                    ProductPrice productPrice = null;
                    try {
                       productPrice  = mapper.readValue(json, ProductPrice.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    return productPrice;
                });
    }

    public Mono<String> priceFallBackMethod(Throwable throwable) {
        System.out.println("Error in Product price service");
        return Mono.just("Error in Product price service");
    }

}
