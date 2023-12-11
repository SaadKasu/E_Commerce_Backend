package com.ecommerce_project.product_service.controllers;

import com.ecommerce_project.product_service.DTOs.ProductRequestDTO;
import com.ecommerce_project.product_service.DTOs.ProductResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping("/")
    @ResponseBody
    public List<ProductResponseDTO> getAllProducts(){
        List<ProductResponseDTO> responseDTOS = new ArrayList<>();
        responseDTOS.add(new ProductResponseDTO("1st Product"));
        return responseDTOS;
    }
    @GetMapping("/Id/{id}")
    public ProductResponseDTO getProductById(@PathVariable String id){
        return null;
    }
    @GetMapping("/category/{category}")
    public List<ProductResponseDTO> getProductByCategory(@PathVariable String category){
        return null;
    }
    @PostMapping("/addProduct")
    @ResponseBody
    public ProductResponseDTO addProduct(@RequestBody  ProductRequestDTO requestDTO){
        return new ProductResponseDTO("Product Added Successfully and name is - "+ requestDTO.getName());
    }
    @PutMapping("/updateProduct")
    @ResponseBody()
    public ProductResponseDTO updateProduct(@RequestBody ProductRequestDTO requestDTO){
        return new ProductResponseDTO("Updated Product with name - "+requestDTO.getName());
    }
    @GetMapping("/limitedResults/")
    @ResponseBody
    public ProductResponseDTO getLimitedResults(@RequestParam(required = false, name = "limit") Integer number, @RequestParam String name){
        return new ProductResponseDTO("This contains "+(number== null ? 0 : number)+" Results for name - "+name);
    }
}
