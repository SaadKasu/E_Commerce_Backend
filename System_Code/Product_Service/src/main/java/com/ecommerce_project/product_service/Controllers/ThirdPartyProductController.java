package com.ecommerce_project.product_service.Controllers;

import com.ecommerce_project.product_service.DTOs.ProductRequestDTO;
import com.ecommerce_project.product_service.DTOs.ProductResponseDTO;
import com.ecommerce_project.product_service.Models.Product;
import com.ecommerce_project.product_service.Services.IProductService;
import com.ecommerce_project.product_service.Services.ThirdPartyProductService;
import com.ecommerce_project.product_service.Utility.ProductUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/Product/ThirdParty")
public class ThirdPartyProductController {
    private IProductService productService;
    @Autowired
    ThirdPartyProductController(ThirdPartyProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        ResponseEntity<List<ProductResponseDTO>> responseEntity;
        List<Product> products = productService.getAllProducts();
        List<ProductResponseDTO> responseDTOS = ProductUtility.convertListOfProductsToDTOs(products);
        return new ResponseEntity<>(responseDTOS,HttpStatusCode.valueOf(200));

        try{
            List<Product> products = productService.getAllProducts();
        }catch (Exception e){

            List<ProductResponseDTO> responseDTOS = new ArrayList<>();
            ProductResponseDTO responseDTO = new ProductResponseDTO();
            responseDTO.setErrorMesssage("Something went wrong...");
            responseDTOS.add(responseDTO);
            return responseEntity = new ResponseEntity<>(responseDTOS,HttpStatusCode.valueOf(500));
        }
        List<ProductResponseDTO> responseDTOs = ProductUtility.convertListOfProductsToDTOs(products);
        return  responseEntity = new ResponseEntity<>(responseDTOs,HttpStatusCode.valueOf(200));

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
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable String id){
        ResponseEntity<ProductResponseDTO> responseEntity;
        Product product = productService.getProductById(id);
        ProductResponseDTO responseDTO = ProductUtility.convertSingleProductToDTO(product);
        return new ResponseEntity<>(responseDTO,HttpStatusCode.valueOf(200));

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
    @GetMapping("/")
    public ResponseEntity<List<ProductResponseDTO>> getLimitedProducts(@RequestParam(name = "limit") int size){
        ResponseEntity<List<ProductResponseDTO>> responseEntity;
        List<Product> products = productService.getLimitedProducts(size);
        List<ProductResponseDTO> responseDTOS = ProductUtility.convertListOfProductsToDTOs(products);
        return new ResponseEntity<>(responseDTOS,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductResponseDTO>> getSortedResults(@RequestParam(name = "sort") String order){
        ResponseEntity<List<ProductResponseDTO>> responseEntity;
        List<Product> products = productService.getSortedResults(order);
        List<ProductResponseDTO> responseDTOS = ProductUtility.convertListOfProductsToDTOs(products);
        return new ResponseEntity<>(responseDTOS,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/Category/{categoryName}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsFromCategory(@PathVariable String categoryName){
        ResponseEntity<List<ProductResponseDTO>> responseEntity;
        List<Product> products = productService.getProductsFromCategory(categoryName);
        List<ProductResponseDTO> responseDTOS = ProductUtility.convertListOfProductsToDTOs(products);
        return new ResponseEntity<>(responseDTOS,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/Categories")
    public ResponseEntity<List<ProductResponseDTO>> getAllCategories(){
        ResponseEntity<List<ProductResponseDTO>> responseEntity;
        List<Product> products = productService.getAllCategories();
        List<ProductResponseDTO> responseDTOS = ProductUtility.convertListOfProductsToDTOs(products);
        return new ResponseEntity<>(responseDTOS,HttpStatusCode.valueOf(200));
    }


    @PostMapping("/AddProduct")
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody  ProductRequestDTO requestDTO){
        ResponseEntity<ProductResponseDTO> responseEntity;
        Product product = ProductUtility.convertSingleDtoToProduct(requestDTO);
        ProductResponseDTO responseDTO = productService.addProduct(product);
        return new ResponseEntity<>(responseDTO,HttpStatusCode.valueOf(200));

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

    @PutMapping("/UpdateProduct")
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestBody  ProductRequestDTO requestDTO) {
        Product product = ProductUtility.convertSingleDtoToProduct(requestDTO);
        product = productService.updateProduct(product);
        ProductResponseDTO responseDTO = ProductUtility.convertSingleProductToDTO(product);
        return new ResponseEntity<>(responseDTO, HttpStatusCode.valueOf(200));
    }
    @DeleteMapping("/DeleteProduct")
    public ResponseEntity<ProductResponseDTO> deleteProduct(@RequestBody  ProductRequestDTO requestDTO) {
        Product product = ProductUtility.convertSingleDtoToProduct(requestDTO);
        product = productService.deleteProduct(product);
        ProductResponseDTO responseDTO = ProductUtility.convertSingleProductToDTO(product);
        return new ResponseEntity<>(responseDTO, HttpStatusCode.valueOf(200));
    }
    
}
