package com.e_commerce_backend.userservice.Controllers;

import com.e_commerce_backend.userservice.DTOs.AuthRequestDTO;
import com.e_commerce_backend.userservice.DTOs.AuthResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IAuthController {
    public ResponseEntity<AuthResponseDTO> createAuthToken(String userName,String password);

    public ResponseEntity<AuthResponseDTO> validateAuthToken(String token, String userId);

}
