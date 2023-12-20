package com.ecommerce_project.product_service.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.lang.model.element.Name;
import java.util.List;
@Data
@Entity(name = "Products")
public class Product extends BaseClass{
    private String title;
    private String description;
    private String image;
    private Long price;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
}
