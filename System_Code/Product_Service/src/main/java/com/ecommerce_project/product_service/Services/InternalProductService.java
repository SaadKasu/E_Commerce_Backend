package com.ecommerce_project.product_service.Services;

import com.ecommerce_project.product_service.Models.Product;
import com.ecommerce_project.product_service.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class InternalProductService implements IProductService{

    private ProductRepository productRepository;
    @Autowired
    public InternalProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Optional<List<Product>> getAllProducts() {
        List<Product> products =  productRepository.findAll();
        return Optional.ofNullable(products);
    }

    @Override
    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<List<Product>> getLimitedProducts(int size) {
        List<Product> products = productRepository.
    }

    @Overridegit s
    public Optional<List<Product>> getSortedResults(String order) {
        List<Product> products = productRepository.findAll()
    }

    @Override
    public Optional<List<Product>> getProductsFromCategory(String category) {
        List<Product> products = productRepository.findBy(Ex)
    }

    @Override
    public Optional<List<String>> getAllCategories() {
        return Optional.empty();
    }

    @Override
    public Optional<Product> addProduct(Product product) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> updateProduct(Product product) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> deleteProduct(Product product) {
        return Optional.empty();
    }
}
