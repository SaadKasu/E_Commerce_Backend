package com.ecommerce_project.product_service.Controllers;

import com.ecommerce_project.product_service.DTOs.ProductRequestDTO;
import com.ecommerce_project.product_service.DTOs.ProductResponseDTO;
import com.ecommerce_project.product_service.Models.Product;
import com.ecommerce_project.product_service.Services.IProductService;
import com.ecommerce_project.product_service.Services.ThirdPartyProductService;
import com.ecommerce_project.product_service.Utility.GeneralUtility;
import com.ecommerce_project.product_service.Utility.ProductUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/ThirdParty/Product")
public class ThirdPartyProductController implements IController{
    private final IProductService productService;
    @Autowired
    ThirdPartyProductController(ThirdPartyProductService productService){
        this.productService = productService;
    }

    @GetMapping("/AllProducts")
    @Override
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        Optional<List<Product>> products = productService.getAllProducts();
        List<ProductResponseDTO> responseDTOS = ProductUtility.convertListOfProductsToDTOs(products);
        return new ResponseEntity<>(responseDTOS,HttpStatusCode.valueOf(200));
    }
    @GetMapping("/ProductById/{id}")
    @Override
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable long id){
        Optional<Product> product = productService.getProductById(id);
        ProductResponseDTO responseDTO = ProductUtility.convertSingleProductToDTO(product);
        return new ResponseEntity<>(responseDTO,HttpStatusCode.valueOf(200));
    }
    @GetMapping("/LimitedProducts")
    @Override
    public ResponseEntity<List<ProductResponseDTO>> getLimitedProducts(@RequestParam(name = "limit") int size){
        Optional<List<Product>> products = productService.getLimitedProducts(size);
        List<ProductResponseDTO> responseDTOS = ProductUtility.convertListOfProductsToDTOs(products);
        return new ResponseEntity<>(responseDTOS,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/SortedProducts")
    @Override
    public ResponseEntity<List<ProductResponseDTO>> getSortedResults(@RequestParam(name = "sort") String order){
        Optional<List<Product>> products = productService.getSortedResults(order);
        List<ProductResponseDTO> responseDTOS = ProductUtility.convertListOfProductsToDTOs(products);
        return new ResponseEntity<>(responseDTOS,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/Category/{categoryName}")
    @Override
    public ResponseEntity<List<ProductResponseDTO>> getProductsFromCategory(@PathVariable String categoryName){
        Optional<List<Product>> products = productService.getProductsFromCategory(categoryName);
        List<ProductResponseDTO> responseDTOS = ProductUtility.convertListOfProductsToDTOs(products);
        return new ResponseEntity<>(responseDTOS,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/Categories")
    @Override
    public ResponseEntity<List<String>> getAllCategories(){
        Optional<List<String>> optionalCategories = productService.getAllCategories();
        List<String> strings = GeneralUtility.convertOptionalToStrings(optionalCategories);
        return new ResponseEntity<>(strings,HttpStatusCode.valueOf(200));
    }


    @PostMapping("/AddProduct")
    @Override
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody  ProductRequestDTO requestDTO){
        Product product = ProductUtility.convertSingleDtoToProduct(requestDTO);
        Optional<Product> optionalProduct = productService.addProduct(product);
        ProductResponseDTO responseDTO = ProductUtility.convertSingleProductToDTO(optionalProduct);
        return new ResponseEntity<>(responseDTO,HttpStatusCode.valueOf(200));
    }

    @PatchMapping("/UpdateProduct")
    @Override
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestBody  ProductRequestDTO requestDTO) {
        Product product = ProductUtility.convertSingleDtoToProduct(requestDTO);
        Optional<Product> optionalProduct = productService.updateProduct(product);
        ProductResponseDTO responseDTO = ProductUtility.convertSingleProductToDTO(optionalProduct);
        return new ResponseEntity<>(responseDTO, HttpStatusCode.valueOf(200));
    }
    @DeleteMapping("/DeleteProduct")
    @Override
    public ResponseEntity<ProductResponseDTO> deleteProduct(@RequestBody  ProductRequestDTO requestDTO) {
        Product product = ProductUtility.convertSingleDtoToProduct(requestDTO);
        Optional<Product> optionalProduct = productService.deleteProduct(product);
        ProductResponseDTO responseDTO = ProductUtility.convertSingleProductToDTO(optionalProduct);
        return new ResponseEntity<>(responseDTO, HttpStatusCode.valueOf(200));
    }

}
