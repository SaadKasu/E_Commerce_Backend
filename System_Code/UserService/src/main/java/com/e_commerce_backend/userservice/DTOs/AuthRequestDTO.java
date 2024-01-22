package com.e_commerce_backend.userservice.DTOs;


import lombok.Data;

@Data
public class AuthRequestDTO {
    private String token;
    private String userName;
    private String password;

}
