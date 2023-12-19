package com.ecommerce_project.product_service.Models;

import lombok.Data;

import java.util.List;

@Data
public class Product extends BaseClass{
    private String title;
    private String category;
    private String description;
    private String image;
    private Long price;
}
