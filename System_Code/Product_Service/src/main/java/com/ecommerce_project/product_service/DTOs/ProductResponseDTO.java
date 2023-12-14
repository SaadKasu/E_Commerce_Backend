package com.ecommerce_project.product_service.DTOs;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private String id;
    private String title;
    private String category;
    private String description;
    private String image;
    private String errorMesssage;
    public ProductResponseDTO (String errorMessage){
        this.errorMesssage = errorMessage;
    }
    public ProductResponseDTO (){
    }
}
