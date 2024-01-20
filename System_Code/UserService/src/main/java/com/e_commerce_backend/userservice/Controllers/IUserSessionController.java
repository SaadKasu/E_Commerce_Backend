package com.e_commerce_backend.userservice.Controllers;

import com.e_commerce_backend.userservice.DTOs.UserSessionRequestDTO;
import com.e_commerce_backend.userservice.DTOs.UserSessionResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IUserSessionController {
    public ResponseEntity<UserSessionResponseDTO> createNewUserSession(UserSessionRequestDTO requestDTO);

    public ResponseEntity<UserSessionResponseDTO> terminateUserSession(UserSessionRequestDTO requestDTO);
}
