package com.supplychain.management.service;

import com.supplychain.management.entity.Product;
import com.supplychain.management.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // ✅ ADD PRODUCT (duplicate check)
    public Product addProduct(Product product) {

        if (productRepository.existsByName(product.getName())) {
            throw new RuntimeException("Product already exists");
        }

        return productRepository.save(product);
    }

    // ✅ GET ALL
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // ✅ GET BY ID
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // ✅ UPDATE
    public Product updateProduct(Long id, Product updatedProduct) {

        Product existing = getProductById(id);

        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setPrice(updatedProduct.getPrice());
        existing.setQuantity(updatedProduct.getQuantity());
        existing.setImageUrl(updatedProduct.getImageUrl());

        return productRepository.save(existing);
    }

    // ✅ DELETE
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // ✅ SEARCH
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    // ✅ PAGINATION
    public Page<Product> getPagedProducts(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }
}