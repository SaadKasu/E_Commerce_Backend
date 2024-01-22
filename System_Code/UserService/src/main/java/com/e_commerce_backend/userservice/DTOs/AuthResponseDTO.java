package com.e_commerce_backend.userservice.DTOs;

import lombok.Data;

import java.util.Date;

@Data
public class AuthResponseDTO {
    private Date endsAt;
    private String token;
    private String userId;
    private String errorMessage;
}
