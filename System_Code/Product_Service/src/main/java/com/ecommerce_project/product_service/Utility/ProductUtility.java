package com.ecommerce_project.product_service.Utility;

import com.ecommerce_project.product_service.DTOs.ProductRequestDTO;
import com.ecommerce_project.product_service.DTOs.ProductResponseDTO;
import com.ecommerce_project.product_service.Models.Product;

import java.util.*;

public class ProductUtility {
    public static ProductResponseDTO convertSingleProductToDTO(Product product){
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setCategory(product.getCategory());
        responseDTO.setId(product.getId());
        responseDTO.setDescription(product.getDescription());
        responseDTO.setTitle(product.getTitle());
        responseDTO.setImage(product.getImage());
        return responseDTO;
    }

    public static List<ProductResponseDTO> convertListOfProductsToDTOs(List<Product> products){
        List<ProductResponseDTO> responseDTOs = new ArrayList<>();
        for (Product product : products){
            responseDTOs.add(convertSingleProductToDTO(product));
        }
        return responseDTOs;
    }

    public static Product convertSingleDtoToProduct(ProductRequestDTO requestDTO){
        Product product = new Product();
        product.setCategory(requestDTO.getCategory());
        product.setDescription(requestDTO.getDescription());
        product.setTitle(requestDTO.getTitle());
        product.setImage(requestDTO.getImage());
        return product;
    }

}
