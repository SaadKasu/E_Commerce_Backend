package com.ecommerce_project.product_service.DTOs;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ProductRequestDTO {
    private long id;
    private String title;
    private Long price;
    private String description;
    private String image;
    private String category;
}
