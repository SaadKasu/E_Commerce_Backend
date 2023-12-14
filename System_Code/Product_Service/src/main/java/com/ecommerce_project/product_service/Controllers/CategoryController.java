package com.ecommerce_project.product_service.Controllers;

import com.ecommerce_project.product_service.DTOs.ProductRequestDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Categories")
public class CategoryController {
    @GetMapping("/{categoryName}")
    public List<ProductRequestDTO> getProductsByCategory(@PathVariable String categoryName){
        return null;
    }
}
