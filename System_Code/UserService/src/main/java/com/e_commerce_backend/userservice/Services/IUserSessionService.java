package com.e_commerce_backend.userservice.Services;

import com.e_commerce_backend.userservice.DTOs.UserSessionRequestDTO;
import com.e_commerce_backend.userservice.DTOs.UserSessionResponseDTO;

public interface IUserSessionService {

    public UserSessionResponseDTO createNewUserSession(UserSessionRequestDTO requestDTO);

    public UserSessionResponseDTO terminateUserSession(UserSessionRequestDTO requestDTO);
}
