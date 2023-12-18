package com.ecommerce_project.product_service.Models;

import lombok.Data;

import java.util.Date;
@Data
public class BaseClass {
    private Long id;
    private String createdBy;
    private String lastModifiedBy;
    private Date createdDate;
    private Date lastModifiedDate;

}
