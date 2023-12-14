package com.ecommerce_project.product_service.Services;

import com.ecommerce_project.product_service.DTOs.ProductRequestDTO;
import com.ecommerce_project.product_service.DTOs.ProductResponseDTO;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    public Optional<ProductResponseDTO> getProductById(String id) throws Exception;
    public Optional<List<ProductResponseDTO>> getAllProducts() throws Exception;
    public Optional<ProductResponseDTO> addProduct(ProductRequestDTO requestDTO) throws Exception;
}
