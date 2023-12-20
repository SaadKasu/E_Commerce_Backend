package com.ecommerce_project.product_service.Utility;

import com.ecommerce_project.product_service.DTOs.ProductRequestDTO;
import com.ecommerce_project.product_service.DTOs.ProductResponseDTO;
import com.ecommerce_project.product_service.Models.Category;
import com.ecommerce_project.product_service.Models.Product;

import java.util.*;

public class ProductUtility {
    public static ProductResponseDTO convertSingleProductToDTO(Optional<Product> optionalProduct){
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        if (optionalProduct.isEmpty()){
            responseDTO.setErrorMesssage("There was an error. Please try again later");
            return responseDTO;
        }
        Product product = optionalProduct.get();
        responseDTO.setCategory(product.getCategory().getName());
        responseDTO.setId(product.getId());
        responseDTO.setDescription(product.getDescription());
        responseDTO.setTitle(product.getTitle());
        responseDTO.setImage(product.getImage());
        responseDTO.setPrice(product.getPrice());
        responseDTO.setId(product.getId());
        return responseDTO;
    }

    public static List<ProductResponseDTO> convertListOfProductsToDTOs(Optional<List<Product>> optionalProducts){
        List<ProductResponseDTO> responseDTOs = new ArrayList<>();
        if (optionalProducts.isEmpty()){
            ProductResponseDTO responseDTO = new ProductResponseDTO();
            responseDTO.setErrorMesssage("Could not return the product. Please try Later");
            responseDTOs.add(responseDTO);
            return responseDTOs;
        }
        List<Product> products = optionalProducts.get();
        for (Product product : products){
            responseDTOs.add(convertSingleProductToDTO(Optional.of(product)));
        }
        return responseDTOs;
    }

    public static Product convertSingleDtoToProduct(ProductRequestDTO requestDTO){
        Product product = new Product();
        product.setCategory(new Category(requestDTO.getCategory()));
        product.setDescription(requestDTO.getDescription());
        product.setTitle(requestDTO.getTitle());
        product.setImage(requestDTO.getImage());
        product.setPrice(requestDTO.getPrice());
        product.setId(requestDTO.getId());
        return product;
    }

}
