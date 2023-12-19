package com.ecommerce_project.product_service.FakeStore;

import lombok.Data;

import java.util.List;

@Data
public class FakeStoreRequestDTO {
    private long id;
    private String title;
    private Long price;
    private String description;
    private String image;
    private String category;
}
