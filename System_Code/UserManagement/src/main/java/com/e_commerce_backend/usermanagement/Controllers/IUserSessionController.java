package com.e_commerce_backend.usermanagement.Controllers;

import com.e_commerce_backend.usermanagement.DTOs.UserSessionRequestDTO;
import com.e_commerce_backend.usermanagement.DTOs.UserSessionResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IUserSessionController {
    public ResponseEntity<UserSessionResponseDTO> createNewUserSession(UserSessionRequestDTO requestDTO);

    public ResponseEntity<UserSessionResponseDTO> terminateUserSession(UserSessionRequestDTO requestDTO);
}
