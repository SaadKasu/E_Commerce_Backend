package com.e_commerce_backend.usermanagement.Services;

import com.e_commerce_backend.usermanagement.DTOs.UserSessionRequestDTO;
import com.e_commerce_backend.usermanagement.DTOs.UserSessionResponseDTO;
import com.e_commerce_backend.usermanagement.Models.User;
import com.e_commerce_backend.usermanagement.Models.UserSession;
import com.e_commerce_backend.usermanagement.Repositories.UserRepository;
import com.e_commerce_backend.usermanagement.Repositories.UserSessionRepository;
import com.e_commerce_backend.usermanagement.Utilities.UserManagementUtility;
import com.e_commerce_backend.usermanagement.Utilities.UserSessionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSessionService implements IUserSessionService{

    private UserSessionRepository userSessionRepository;
    private UserRepository userRepository;
    @Autowired
    public UserSessionService(UserSessionRepository userSessionRepository, UserRepository userRepository){
        this.userSessionRepository = userSessionRepository;
        this.userRepository = userRepository;
    }
    @Override
    public UserSessionResponseDTO createNewUserSession(UserSessionRequestDTO requestDTO) {
        UserSessionResponseDTO responseDTO = UserSessionUtility.checkIfRequiredFieldsPresentToCreateSession(requestDTO);
        if (!responseDTO.getErrorMessage().isBlank())
            return responseDTO;

        Optional<User> optionalUser = userRepository.checkIfUserWithUserNameExists(requestDTO.getUserName());

        responseDTO = UserSessionUtility.checkIfUserExists(optionalUser);
        if (!responseDTO.getErrorMessage().isBlank()){
            return responseDTO;
        }

        User existingUser = optionalUser.get();
        Optional<List<UserSession>> optionalUserSessions = userSessionRepository.getActiveUserSessions(existingUser.getId());

        responseDTO = UserSessionUtility.checkIfUserHasLessThanTwoActiveSessions(optionalUserSessions);
        if (!responseDTO.getErrorMessage().isBlank()){
            return responseDTO;
        }

        UserSession userSession = createUserSession(existingUser);

        UserSession insertedUserSession = userSessionRepository.save(userSession);

        return UserSessionUtility.convertUserSessionToResponseDTO(insertedUserSession);

    }

    @Override
    public UserSessionResponseDTO terminateUserSession(UserSessionRequestDTO requestDTO) {
        UserSessionResponseDTO responseDTO = UserSessionUtility.checkIfRequiredFieldsPresentToTerminateSession(requestDTO);
        if (!responseDTO.getErrorMessage().isBlank())
            return responseDTO;

        Optional<UserSession> optionalUserSession = userSessionRepository.getUserSessionWithSessionId(requestDTO.getSessionToken());

        responseDTO = UserSessionUtility.checkIfUserSessionForTokenExist(optionalUserSession);
        if (!responseDTO.getErrorMessage().isBlank())
            return responseDTO;

        UserSession userSession = optionalUserSession.get();

        if (!userSession.isActive())
            return UserSessionUtility.convertUserSessionToResponseDTO(userSession);

        UserSessionUtility.setIsActiveFlag(userSession,false);
        UserSessionUtility.setUpdateAudits(userSession);

        UserSession updatedUserSession = userSessionRepository.save(userSession);

        return UserSessionUtility.convertUserSessionToResponseDTO(updatedUserSession);

    }


    private UserSession createUserSession(User user){
        UserSession userSession = new UserSession();
        userSession.setUser(user);
        UserSessionUtility.setCreateAudits(userSession);
        UserSessionUtility.setIsActiveFlag(userSession,true);
        return userSession;
    }

}
