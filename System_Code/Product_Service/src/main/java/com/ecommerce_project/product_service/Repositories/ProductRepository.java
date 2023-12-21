package com.ecommerce_project.product_service.Repositories;

import com.ecommerce_project.product_service.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "Select * from Products order by Id :order", nativeQuery = true)
    List<Product> findSortedProduts(@Param("order") String order);

    @Query(value = "Select * from Products where category = :category", nativeQuery = true)
    List<Product> findProductsInACategory(@Param("category") String category);

    @Query(value = "Select * from Products limit =:limit", nativeQuery = true)
    List<Product> findLimitedProducts(@Param("limit") int limit);

    @Query(value = "Select DISTINCT category from Products", nativeQuery = true)
    List<Product> findDistinctByCategory();

}
