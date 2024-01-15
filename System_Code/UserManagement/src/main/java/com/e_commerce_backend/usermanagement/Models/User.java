package com.e_commerce_backend.usermanagement.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Entity(name = "Users")
@Data
public class User extends BaseModel{
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private String middleName;
    @Column(nullable = false, unique = true)
    private String userName;
    @Enumerated(EnumType.STRING )
    private Gender gender;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false, unique = true)
    private String emailAddress;
    @Column(nullable = false, unique = true)
    private String mobileNumber;
    @Column(nullable = false)
    private boolean isDeleted;
    @Column(nullable = false)
    private String password;
}
