package com.e_commerce_backend.userservice.Controllers;

import com.e_commerce_backend.userservice.DTOs.UserRequestDTO;
import com.e_commerce_backend.userservice.DTOs.UserResponseDTO;
import com.e_commerce_backend.userservice.Models.User;
import com.e_commerce_backend.userservice.Services.IUserService;
import com.e_commerce_backend.userservice.Services.UserService;
import com.e_commerce_backend.userservice.Utilities.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Users")
public class UserController implements IUserController {
    private final IUserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/CreateUser")
    @Override
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO requestDTO) {
        User user = UserUtility.convertUserRequestDTOToUserObject(requestDTO);
        Optional<User> optionalUser = userService.createUser(user);
        UserResponseDTO responseDTO = UserUtility.convertUserObjectToUserResponseDTO(optionalUser);
        return UserUtility.convertResponseDTOToResponseEntity(responseDTO);
    }
    @PatchMapping("/UpdateUser")
    @Override
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO requestDTO) {
        User user = UserUtility.convertUserRequestDTOToUserObject(requestDTO);
        Optional<User> optionalUser = userService.updateUser(user);
        UserResponseDTO responseDTO = UserUtility.convertUserObjectToUserResponseDTO(optionalUser);
        return UserUtility.convertResponseDTOToResponseEntity(responseDTO);
    }
    @DeleteMapping("/DeleteUser")
    @Override
    public ResponseEntity<UserResponseDTO> deleteUser(@RequestBody UserRequestDTO requestDTO) {
        User user = UserUtility.convertUserRequestDTOToUserObject(requestDTO);
        Optional<User> optionalUser = userService.deleteUser(user);
        UserResponseDTO responseDTO = UserUtility.convertUserObjectToUserResponseDTO(optionalUser);
        return UserUtility.convertResponseDTOToResponseEntity(responseDTO);
    }

    @Override
    @GetMapping("/GetUser")
    public ResponseEntity<UserResponseDTO> getUserDetails(@RequestParam String userId) {
        UserRequestDTO userRequestDTO = UserUtility.createUserRequestDTOWithUserId(userId);
        User user = UserUtility.convertUserRequestDTOToUserObject(userRequestDTO);
        Optional<User> optionalUser = userService.getUserDetails(user);
        UserResponseDTO responseDTO = UserUtility.convertUserObjectToUserResponseDTO(optionalUser);
        return UserUtility.convertResponseDTOToResponseEntity(responseDTO);
    }

    @Override
    @GetMapping("/GetAllUsers")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        Optional<List<User>> users = userService.getAllUsers();
        List<UserResponseDTO> userResponseDTOS = UserUtility.convertListOfUsersToResponseDTOs(users);
        return UserUtility.convertListOfReponseDTOsToResponseEntity(userResponseDTOS);
    }

    @Override
    @PostMapping("/LoginUser")
    public ResponseEntity<UserResponseDTO> loginUser(@RequestBody UserRequestDTO requestDTO){
        User user = UserUtility.convertUserRequestDTOToUserObject(requestDTO);
        Optional<User> optionalUser = userService.loginUser(user);
        UserResponseDTO responseDTO = UserUtility.convertUserObjectToUserResponseDTO(optionalUser);
        return UserUtility.convertResponseDTOToResponseEntity(responseDTO);
    }
}
