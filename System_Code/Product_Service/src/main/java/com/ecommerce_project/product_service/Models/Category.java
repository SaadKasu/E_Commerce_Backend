package com.ecommerce_project.product_service.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "Categories")
public class Category extends BaseClass{
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> product;
    public Category(String name){
        this.name = name;
    }

    public Category() {

    }
}
