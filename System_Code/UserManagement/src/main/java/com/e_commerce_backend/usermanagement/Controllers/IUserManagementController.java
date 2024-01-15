package com.e_commerce_backend.usermanagement.Controllers;

import com.e_commerce_backend.usermanagement.DTOs.UserManagementRequestDTO;
import com.e_commerce_backend.usermanagement.DTOs.UserManagementResponseDTO;
import com.e_commerce_backend.usermanagement.DTOs.UserSessionRequestDTO;
import com.e_commerce_backend.usermanagement.DTOs.UserSessionResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IUserManagementController {

    public ResponseEntity<UserManagementResponseDTO> registerNewUser(UserManagementRequestDTO requestDTO);

    public ResponseEntity<UserManagementResponseDTO> updateExistingUser(UserManagementRequestDTO requestDTO);

    public ResponseEntity<UserManagementResponseDTO> deleteExistingUser(UserManagementRequestDTO requestDTO);

    public ResponseEntity<UserManagementResponseDTO> getUserDetails(String userName);

    public ResponseEntity<List<UserManagementResponseDTO>> getAllUsers();
}
