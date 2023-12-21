package com.ecommerce_project.product_service.Controllers;

import com.ecommerce_project.product_service.DTOs.ProductRequestDTO;
import com.ecommerce_project.product_service.DTOs.ProductResponseDTO;
import com.ecommerce_project.product_service.Services.IProductService;
import com.ecommerce_project.product_service.Services.InternalProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Internal/Product")
public class InternalProductController implements IController{
    private IProductService productService;
    @Autowired
    public InternalProductController(InternalProductService productService){
        this.productService = productService;
    }


    @Override
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return null;
    }

    @Override
    public ResponseEntity<ProductResponseDTO> getProductById(long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<ProductResponseDTO>> getLimitedProducts(int size) {
        return null;
    }

    @Override
    public ResponseEntity<List<ProductResponseDTO>> getSortedResults(String order) {
        return null;
    }

    @Override
    public ResponseEntity<List<ProductResponseDTO>> getProductsFromCategory(String categoryName) {
        return null;
    }

    @Override
    public ResponseEntity<List<String>> getAllCategories() {
        return null;
    }

    @Override
    public ResponseEntity<ProductResponseDTO> addProduct(ProductRequestDTO requestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ProductResponseDTO> updateProduct(ProductRequestDTO requestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ProductResponseDTO> deleteProduct(ProductRequestDTO requestDTO) {
        return null;
    }
}
