package com.ecommerce_project.product_service.Services;

import com.ecommerce_project.product_service.DTOs.ProductRequestDTO;
import com.ecommerce_project.product_service.DTOs.ProductResponseDTO;
import com.ecommerce_project.product_service.Models.Product;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    public Optional<List<Product>> getAllProducts();
    public Optional<Product> getProductById(long id);

    public Optional<List<Product>> getLimitedProducts(int size);
    public Optional<List<Product>> getSortedResults(String order);

    public Optional<List<Product>> getProductsFromCategory(String category);

    public Optional<List<String>> getAllCategories();

    public Optional<Product> addProduct(Product product);
    public Optional<Product> updateProduct(Product product);
    public Optional<Product> deleteProduct(Product product);
}
