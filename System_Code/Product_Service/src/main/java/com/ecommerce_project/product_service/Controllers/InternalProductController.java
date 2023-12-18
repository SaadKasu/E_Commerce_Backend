package com.ecommerce_project.product_service.Controllers;

import com.ecommerce_project.product_service.Services.IProductService;
import com.ecommerce_project.product_service.Services.InternalProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Product/Internal")
public class InternalProductController {
    private IProductService productService;
    @Autowired
    public InternalProductController(InternalProductService internalProductService){
        this.productService = internalProductService;
    }

}
