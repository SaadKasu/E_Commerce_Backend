package com.ecommerce_project.product_service.Adapters;

import com.ecommerce_project.product_service.Models.Product;

import java.util.List;

public interface IThirdPartyAdapter {
    public List<Product> getAllProducts();
    public Product getProductById(long id);
    public List<Product> getTopXResults(int limit);
    public List<Product> getSortedResults(String sortOrder);
    public List<Product> getAllProductsInOneCategory(String category);
    public Product addNewProduct(Product product);
    public Product updateAProduct(Product product);
    public Product deleteAProduct(Product product);
    public List<String> getAllCategories();

}
