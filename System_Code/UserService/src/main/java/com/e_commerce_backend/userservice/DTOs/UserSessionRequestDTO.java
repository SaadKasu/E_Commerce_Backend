package com.e_commerce_backend.userservice.DTOs;

import lombok.Data;

@Data
public class UserSessionRequestDTO {
    private String userId;
    private String sessionToken;
    private String userName;
    private String password;
}
