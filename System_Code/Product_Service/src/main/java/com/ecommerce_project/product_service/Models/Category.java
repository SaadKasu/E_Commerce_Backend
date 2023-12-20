package com.ecommerce_project.product_service.Models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "Categories")
public class Category extends BaseClass{
    private String name;
    public Category(String name){
        this.name = name;
    }

    public Category() {

    }
}
