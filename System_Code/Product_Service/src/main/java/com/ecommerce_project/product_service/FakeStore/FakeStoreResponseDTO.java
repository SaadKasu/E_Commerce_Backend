package com.ecommerce_project.product_service.FakeStore;

import lombok.Data;

@Data
public class FakeStoreResponseDTO {
    private long id;
    private String title;
    private String category;
    private String description;
    private String image;
    private Long price;
}
