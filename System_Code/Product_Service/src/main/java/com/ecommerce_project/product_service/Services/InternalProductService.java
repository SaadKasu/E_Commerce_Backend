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
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product getProductById(String id) {
        return null;
    }

    @Override
    public List<Product> getLimitedProducts(int size) {
        return null;
    }

    @Override
    public List<Product> getSortedResults(String order) {
        return null;
    }

    @Override
    public List<Product> getProductsFromCategory(String category) {
        return null;
    }

    @Override
    public List<Product> getAllCategories() {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(Product product) {
        return null;
    }
}
