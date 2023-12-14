package com.ecommerce_project.product_service.Controllers;

import com.ecommerce_project.product_service.DTOs.ProductRequestDTO;
import com.ecommerce_project.product_service.DTOs.ProductResponseDTO;
import com.ecommerce_project.product_service.Services.IProductService;
import com.ecommerce_project.product_service.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private IProductService productService;
    @Autowired
    ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        ResponseEntity<List<ProductResponseDTO>> responseEntity;
        try{
            Optional<List<ProductResponseDTO>> responseDTOS = productService.getAllProducts();
            if (responseDTOS.isPresent()){
                responseEntity = new ResponseEntity<>(responseDTOS.get(),HttpStatusCode.valueOf(200));
            }
            else{
                responseEntity = new ResponseEntity<>(new ArrayList<>((Collection) new ProductResponseDTO("No Response From Server")),HttpStatusCode.valueOf(404));
            }
            return responseEntity;
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<>(new ArrayList<>((Collection) new ProductResponseDTO("Something went wrong")),HttpStatusCode.valueOf(500));
            return responseEntity;
        }
    }
    @GetMapping("/Id/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable String id){
        ResponseEntity<ProductResponseDTO> responseEntity;
        try {
            Optional<ProductResponseDTO> responseDTO = productService.getProductById(id);
            if (responseDTO.isPresent()) {
                responseEntity = new ResponseEntity<>(responseDTO.get(), HttpStatusCode.valueOf(200));
            } else {
                responseEntity = new ResponseEntity<>(new ProductResponseDTO("No Product with Id"), HttpStatusCode.valueOf(404));
            }
            return responseEntity;
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<>(new ProductResponseDTO("Something went Wrong..."),HttpStatusCode.valueOf(500));
            return responseEntity;
        }
    }
//    @GetMapping("/category/{category}")
//    public List<ProductResponseDTO> getProductByCategory(@PathVariable String category){
//        return null;
//    }
    @PostMapping("/addProduct")
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody  ProductRequestDTO requestDTO){
        ResponseEntity<ProductResponseDTO> responseEntity;
        try{
            Optional<ProductResponseDTO> responseDTO = productService.addProduct(requestDTO);
            if (responseDTO.isPresent()){
                MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
                headers.add("Content","Hi Sir");
                responseEntity = new ResponseEntity<>(responseDTO.get(),headers,HttpStatusCode.valueOf(200));
            }
            else{
                responseEntity = new ResponseEntity<>(new ProductResponseDTO("Couldn't add the product."),HttpStatusCode.valueOf(502));
            }
            return responseEntity;
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<>(new ProductResponseDTO("Something went wrong..."),HttpStatusCode.valueOf(500));
            return responseEntity;
        }
    }

//    @PutMapping("/updateProduct")
//    @ResponseBody()
//    public ProductResponseDTO updateProduct(@RequestBody ProductRequestDTO requestDTO){
//        return new ProductResponseDTO("Updated Product with name - "+requestDTO.getName());
//    }
//    @GetMapping("/limitedResults/")
//    @ResponseBody
//    public ProductResponseDTO getLimitedResults(@RequestParam(required = false, name = "limit") Integer number, @RequestParam String name){
//        return new ProductResponseDTO("This contains "+(number== null ? 0 : number)+" Results for name - "+name);
//    }
}
