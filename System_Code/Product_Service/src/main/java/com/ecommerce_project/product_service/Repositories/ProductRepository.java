package com.ecommerce_project.product_service.Repositories;

import com.ecommerce_project.product_service.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
