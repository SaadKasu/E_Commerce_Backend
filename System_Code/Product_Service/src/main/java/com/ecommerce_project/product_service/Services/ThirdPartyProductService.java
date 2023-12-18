package com.ecommerce_project.product_service.Services;

import com.ecommerce_project.product_service.DTOs.ProductRequestDTO;
import com.ecommerce_project.product_service.DTOs.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ThirdPartyProductService implements IProductService {
    private RestTemplate restTemplate;

    @Autowired
    public ThirdPartyProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }
    public Optional<ProductResponseDTO> getProductById(String productId) throws Exception{
             ProductResponseDTO responseDTO = restTemplate.getForEntity("https://fakestoreapi.com/products/"+productId, ProductResponseDTO.class).getBody();
            return Optional.ofNullable(responseDTO);
    }

    public Optional<List<ProductResponseDTO>> getAllProducts() throws Exception{
        ProductResponseDTO [] responseDTOS = restTemplate.getForEntity("https://fakestoreapi.com/products", ProductResponseDTO[].class).getBody();
        return Optional.ofNullable(List.of(responseDTOS));
    }

    @Override
    public Optional<ProductResponseDTO> addProduct(ProductRequestDTO requestDTO) throws Exception {
        ProductResponseDTO responseDTO = restTemplate.postForEntity("https://fakestoreapi.com/products",requestDTO,ProductResponseDTO.class).getBody();
        return Optional.ofNullable(responseDTO);
    }


}
