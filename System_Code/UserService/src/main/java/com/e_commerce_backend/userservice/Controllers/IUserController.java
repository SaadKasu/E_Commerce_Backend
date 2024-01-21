package com.e_commerce_backend.userservice.Controllers;

import com.e_commerce_backend.userservice.DTOs.UserRequestDTO;
import com.e_commerce_backend.userservice.DTOs.UserResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserController {

    public ResponseEntity<UserResponseDTO> createUser(UserRequestDTO requestDTO);

    public ResponseEntity<UserResponseDTO> updateUser(UserRequestDTO requestDTO);

    public ResponseEntity<UserResponseDTO> deleteUser(UserRequestDTO requestDTO);

    public ResponseEntity<UserResponseDTO> getUserDetails(String userId);

    public ResponseEntity<List<UserResponseDTO>> getAllUsers();

    public ResponseEntity<UserResponseDTO> loginUser(UserRequestDTO requestDTO);
}
