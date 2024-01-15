package com.e_commerce_backend.usermanagement.Controllers;

import com.e_commerce_backend.usermanagement.DTOs.UserSessionRequestDTO;
import com.e_commerce_backend.usermanagement.DTOs.UserSessionResponseDTO;
import com.e_commerce_backend.usermanagement.Services.IUserSessionService;
import com.e_commerce_backend.usermanagement.Services.UserSessionService;
import com.e_commerce_backend.usermanagement.Utilities.UserSessionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Users/UserSessions")
public class UserSessionController implements IUserSessionController{

    private final IUserSessionService userSessionService;
    @Autowired
    public UserSessionController(UserSessionService userSessionService){
        this.userSessionService = userSessionService;
    }
    @Override
    @PostMapping("/CreateSession")
    public ResponseEntity<UserSessionResponseDTO> createNewUserSession(@RequestBody UserSessionRequestDTO requestDTO) {
        UserSessionResponseDTO responseDTO = userSessionService.createNewUserSession(requestDTO);
        return UserSessionUtility.convertResponseDTOToResponseEntity(responseDTO);
    }

    @Override
    @DeleteMapping("/TerminateSession")
    public ResponseEntity<UserSessionResponseDTO> terminateUserSession(@RequestBody UserSessionRequestDTO requestDTO) {
        UserSessionResponseDTO responseDTO = userSessionService.terminateUserSession(requestDTO);
        return UserSessionUtility.convertResponseDTOToResponseEntity(responseDTO);
    }
}
