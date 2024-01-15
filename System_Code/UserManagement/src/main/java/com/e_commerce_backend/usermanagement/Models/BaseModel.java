package com.e_commerce_backend.usermanagement.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@MappedSuperclass
@Data
public class BaseModel {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    protected String id;
    @CreatedBy
    @Column(nullable = false)
    protected String createdBy;
    @LastModifiedBy
    @Column(nullable = false)
    protected String lastModifiedBy;
    @CreatedDate
    @Column(nullable = false)
    protected Date createdDate;
    @LastModifiedDate
    @Column(nullable = false)
    protected Date lastModifiedAt;
}
