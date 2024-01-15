package com.e_commerce_backend.usermanagement.Controllers;

import com.e_commerce_backend.usermanagement.DTOs.UserManagementRequestDTO;
import com.e_commerce_backend.usermanagement.DTOs.UserManagementResponseDTO;
import com.e_commerce_backend.usermanagement.DTOs.UserSessionRequestDTO;
import com.e_commerce_backend.usermanagement.DTOs.UserSessionResponseDTO;
import com.e_commerce_backend.usermanagement.Models.User;
import com.e_commerce_backend.usermanagement.Services.IUserManagementService;
import com.e_commerce_backend.usermanagement.Services.UserManagementService;
import com.e_commerce_backend.usermanagement.Utilities.UserManagementUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Users/UserManagement")
public class UserManagementController implements IUserManagementController{
    private final IUserManagementService userManagementService;

    @Autowired
    public UserManagementController(UserManagementService userManagementService){
        this.userManagementService = userManagementService;
    }
    @PostMapping("/CreateUser")
    @Override
    public ResponseEntity<UserManagementResponseDTO> registerNewUser(@RequestBody UserManagementRequestDTO requestDTO) {
        UserManagementResponseDTO responseDTO = userManagementService.createNewUser(requestDTO);
        return UserManagementUtility.convertResponseDTOToResponseEntity(responseDTO);
    }
    @PatchMapping("/UpdateUser")
    @Override
    public ResponseEntity<UserManagementResponseDTO> updateExistingUser(@RequestBody UserManagementRequestDTO requestDTO) {
        UserManagementResponseDTO responseDTO = userManagementService.updateExistingUser(requestDTO);
        return UserManagementUtility.convertResponseDTOToResponseEntity(responseDTO);
    }
    @DeleteMapping("/DeleteUser")
    @Override
    public ResponseEntity<UserManagementResponseDTO> deleteExistingUser(@RequestBody UserManagementRequestDTO requestDTO) {
        UserManagementResponseDTO responseDTO = userManagementService.deleteExistingUser(requestDTO);
        return UserManagementUtility.convertResponseDTOToResponseEntity(responseDTO);
    }

    @Override
    @GetMapping("/GetUser")
    public ResponseEntity<UserManagementResponseDTO> getUserDetails(@RequestParam String userName) {
        UserManagementRequestDTO requestDTO = UserManagementUtility.createUserManagementRequestDTOFromUserName(userName);
        UserManagementResponseDTO responseDTO = userManagementService.getUserDetails(requestDTO);
        return UserManagementUtility.convertResponseDTOToResponseEntity(responseDTO);
    }

    @Override
    @GetMapping("/GetAllUsers")
    public ResponseEntity<List<UserManagementResponseDTO>> getAllUsers() {
        List<UserManagementResponseDTO> responseDTOS = userManagementService.getAllUsers();
        return UserManagementUtility.convertListOfReponseDTOsToResponseEntity(responseDTOS);
    }
}
