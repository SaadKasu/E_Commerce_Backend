package com.e_commerce_backend.usermanagement.Services;

import com.e_commerce_backend.usermanagement.DTOs.UserManagementRequestDTO;
import com.e_commerce_backend.usermanagement.DTOs.UserManagementResponseDTO;
import com.e_commerce_backend.usermanagement.Models.User;

import java.util.List;
import java.util.Optional;

public interface IUserManagementService {
    public UserManagementResponseDTO createNewUser(UserManagementRequestDTO user);
    public UserManagementResponseDTO updateExistingUser(UserManagementRequestDTO user);
    public UserManagementResponseDTO deleteExistingUser(UserManagementRequestDTO user);
    public UserManagementResponseDTO getUserDetails(UserManagementRequestDTO user);
    public List<UserManagementResponseDTO> getAllUsers();
}
