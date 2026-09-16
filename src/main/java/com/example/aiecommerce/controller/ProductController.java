package com.example.aiecommerce.controller;

import com.example.aiecommerce.dto.ProductRequest;
import com.example.aiecommerce.entity.Product;
import com.example.aiecommerce.service.KafkaProducerService;
import com.example.aiecommerce.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;
    private final KafkaProducerService producerService;

    // Unified constructor injection for both services
    public ProductController(ProductService service, KafkaProducerService producerService) {
        this.service = service;
        this.producerService = producerService;
    }

    @GetMapping
    public List<Product> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Product one(@PathVariable Long id) {
        return service.find(id);
    }

    @PostMapping
    public ResponseEntity<Product> create( @RequestBody ProductRequest r) {
        Product createdProduct = service.create(r);
        
        // Publish event to Kafka
        producerService.sendMessage(
            "ecommerce-events", 
            "PRODUCT_CREATED: ID=" + createdProduct.getId() + ", Name=" + createdProduct.getName()
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id,@RequestBody ProductRequest r) {
        Product updatedProduct = service.update(id, r);
        
        // Publish update event to Kafka
        producerService.sendMessage(
            "ecommerce-events", 
            "PRODUCT_UPDATED: ID=" + updatedProduct.getId() + ", Name=" + updatedProduct.getName()
        );
        
        return updatedProduct;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        
        // Publish deletion event to Kafka
        producerService.sendMessage(
            "ecommerce-events", 
            "PRODUCT_DELETED: ID=" + id
        );
        
        return ResponseEntity.noContent().build();
    }
}