package com.ecommerce_project.product_service.Services;

import com.ecommerce_project.product_service.DTOs.ProductRequestDTO;
import com.ecommerce_project.product_service.DTOs.ProductResponseDTO;
import com.ecommerce_project.product_service.Models.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    public List<Product> getAllProducts();
    public Product getProductById(String id);

    public List<Product> getLimitedProducts(int size);
    public List<Product> getSortedResults(String order);

    public List<Product> getProductsFromCategory(String category);

    public List<Product> getAllCategories();

    public Product addProduct(Product product);
    public Product updateProduct(Product product);
    public Product deleteProduct(Product product);
}
