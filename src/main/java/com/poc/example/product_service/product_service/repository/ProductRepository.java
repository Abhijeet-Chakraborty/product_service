package com.poc.example.product_service.product_service.repository;

import com.poc.example.product_service.product_service.model.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}
