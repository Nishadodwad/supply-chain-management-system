package com.supplychain.management.config;

import com.supplychain.management.dto.*;
import com.supplychain.management.entity.Product;
import com.supplychain.management.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;

    public DataLoader(ProductRepository productRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) {

        // prevent duplicate seeding
        if (productRepository.count() > 2) {
            return;
        }

        String url = "https://dummyjson.com/products";

        ExternalProductResponse response =
                restTemplate.getForObject(url, ExternalProductResponse.class);

        if (response != null && response.getProducts() != null) {

            for (ExternalProduct ext : response.getProducts()) {

                Product p = new Product();
                p.setName(ext.getTitle());
                p.setDescription(ext.getDescription());
                p.setPrice(ext.getPrice());
                p.setQuantity(ext.getStock());
                p.setImageUrl(ext.getThumbnail());

                productRepository.save(p);
            }

            System.out.println("✅ Products seeded successfully!");
        }
    }
}