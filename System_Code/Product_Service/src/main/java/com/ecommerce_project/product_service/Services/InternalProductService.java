package com.ecommerce_project.product_service.Services;

import com.ecommerce_project.product_service.DTOs.ProductRequestDTO;
import com.ecommerce_project.product_service.DTOs.ProductResponseDTO;
import com.ecommerce_project.product_service.Models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class InternalProductService implements IProductService{

    @Override
    public Optional<List<Product>> getAllProducts() {
        return Optional.empty();
    }

    @Override
    public Optional<Product> getProductById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Product>> getLimitedProducts(int size) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Product>> getSortedResults(String order) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Product>> getProductsFromCategory(String category) {
        return Optional.empty();
    }

    @Override
    public Optional<List<String>> getAllCategories() {
        return Optional.empty();
    }

    @Override
    public Optional<Product> addProduct(Product product) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> updateProduct(Product product) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> deleteProduct(Product product) {
        return Optional.empty();
    }
}
