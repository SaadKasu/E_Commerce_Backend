package com.ecommerce_project.product_service.Services;

import com.ecommerce_project.product_service.Adapters.IThirdPartyAdapter;
import com.ecommerce_project.product_service.DTOs.ProductRequestDTO;
import com.ecommerce_project.product_service.DTOs.ProductResponseDTO;
import com.ecommerce_project.product_service.FakeStore.FakeStoreAdapter;
import com.ecommerce_project.product_service.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ThirdPartyProductService implements IProductService {
    private final IThirdPartyAdapter thirdPartyAdapter;

    @Autowired
    public ThirdPartyProductService(FakeStoreAdapter fakeStoreAdapter){
        this.thirdPartyAdapter = fakeStoreAdapter;
    }


    public Optional<List<Product>> getAllProducts(){
        List<Product> products = thirdPartyAdapter.getAllProducts();
        return Optional.of(products);
    }

    public Optional<Product> getProductById(String productId){
        Product product = thirdPartyAdapter.getProductById(productId);
        return Optional.ofNullable(product);
    }

    @Override
    public Optional<List<Product>> getLimitedProducts(int size) {
        List<Product> products = thirdPartyAdapter.getTopXResults(size);
        return Optional.ofNullable(products);
    }

    @Override
    public Optional<List<Product>> getSortedResults(String order) {
        List<Product> products = thirdPartyAdapter.getSortedResults(order);
        return Optional.ofNullable(products);
    }

    @Override
    public Optional<List<Product>> getProductsFromCategory(String category) {
        List<Product> products = thirdPartyAdapter.getAllProductsInOneCategory(category);
        return Optional.ofNullable(products);
    }

    @Override
    public Optional<List<String>> getAllCategories() {
        List<String> strings = thirdPartyAdapter.getAllCategories();
        return Optional.ofNullable(strings);
    }

    @Override
    public Optional<Product> addProduct(Product product) {
        product = thirdPartyAdapter.addNewProduct(product);
        return Optional.ofNullable(product);
    }

    @Override
    public Optional<Product> updateProduct(Product product) {
        product = thirdPartyAdapter.updateAProduct(product);
        return Optional.ofNullable(product);
    }

    @Override
    public Optional<Product> deleteProduct(Product product) {
        product = thirdPartyAdapter.deleteAProduct(product);
        return Optional.ofNullable(product);
    }



}
