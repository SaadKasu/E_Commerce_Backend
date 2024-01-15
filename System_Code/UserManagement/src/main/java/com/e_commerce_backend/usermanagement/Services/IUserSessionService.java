package com.e_commerce_backend.usermanagement.Services;

import com.e_commerce_backend.usermanagement.DTOs.UserSessionRequestDTO;
import com.e_commerce_backend.usermanagement.DTOs.UserSessionResponseDTO;

public interface IUserSessionService {

    public UserSessionResponseDTO createNewUserSession(UserSessionRequestDTO requestDTO);

    public UserSessionResponseDTO terminateUserSession(UserSessionRequestDTO requestDTO);
}
