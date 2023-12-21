package com.ecommerce_project.product_service.Controllers;

import com.ecommerce_project.product_service.DTOs.ProductRequestDTO;
import com.ecommerce_project.product_service.DTOs.ProductResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IController {
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts();
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable long id);
    public ResponseEntity<List<ProductResponseDTO>> getLimitedProducts(@RequestParam(name = "limit") int size);
    public ResponseEntity<List<ProductResponseDTO>> getSortedResults(@RequestParam(name = "sort") String order);
    public ResponseEntity<List<ProductResponseDTO>> getProductsFromCategory(@PathVariable String categoryName);
    public ResponseEntity<List<String>> getAllCategories();
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody ProductRequestDTO requestDTO);
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestBody  ProductRequestDTO requestDTO);
    public ResponseEntity<ProductResponseDTO> deleteProduct(@RequestBody  ProductRequestDTO requestDTO);
}
