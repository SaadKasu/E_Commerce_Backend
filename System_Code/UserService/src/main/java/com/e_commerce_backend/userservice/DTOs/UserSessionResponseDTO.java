package com.e_commerce_backend.userservice.DTOs;

import lombok.Data;

@Data
public class UserSessionResponseDTO {
    private String sessionToken;
    private String userId;
    private String errorMessage;
    private boolean isActive;
}
