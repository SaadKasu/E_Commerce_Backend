package com.ecommerce_project.product_service.DTOs;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private String responseMessage;
    public ProductResponseDTO(String msg){
        responseMessage = msg;
    }
}
