package com.e_commerce_backend.userservice.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity(name = "Sessions")
@Data
public class Session extends BaseModel{
    @Column(nullable = false,name = "ends_At")
    private Date ends_At;
    @Column(nullable = false,unique = true)
    private String token;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;
}
