package com.e_commerce_backend.userservice.Controllers;

import com.e_commerce_backend.userservice.DTOs.AuthRequestDTO;
import com.e_commerce_backend.userservice.DTOs.AuthResponseDTO;
import com.e_commerce_backend.userservice.Models.Session;
import com.e_commerce_backend.userservice.Services.AuthService;
import com.e_commerce_backend.userservice.Services.IAuthService;
import com.e_commerce_backend.userservice.Utilities.AuthUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Authenticate")
public class AuthController implements IAuthController{

    private final IAuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }
    @PostMapping("/CreateToken")
    @Override
    public ResponseEntity<AuthResponseDTO> createAuthToken(@RequestParam (name = "userName") String userName, @RequestParam (name = "password") String password) {
        Optional<Session> optionalSession = authService.createAuthToken(userName,password);
        AuthResponseDTO responseDTO = AuthUtility.convertSessionToResponseDTO(optionalSession);
        return AuthUtility.convertResponseDTOToEntity(responseDTO);
    }
    @GetMapping("/ValidateToken")
    @Override
    public ResponseEntity<AuthResponseDTO> validateAuthToken(@RequestParam (name = "token") String token, @RequestParam (name = "userId") String userId) {
        Optional<Session> optionalSession = authService.validateAuthToken(token,userId);
        AuthResponseDTO responseDTO = AuthUtility.convertSessionToResponseDTO(optionalSession);
        return AuthUtility.convertResponseDTOToEntity(responseDTO);
    }
}
