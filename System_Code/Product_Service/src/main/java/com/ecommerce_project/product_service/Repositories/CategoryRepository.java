package com.ecommerce_project.product_service.Repositories;

import com.ecommerce_project.product_service.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
