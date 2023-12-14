package com.ecommerce_project.product_service.DTOs;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ProductRequestDTO {
    private String title;
    private String price;
    private String description;
    private String image;
    private String category;
}
