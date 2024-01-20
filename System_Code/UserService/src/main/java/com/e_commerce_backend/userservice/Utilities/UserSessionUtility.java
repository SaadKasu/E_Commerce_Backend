package com.e_commerce_backend.userservice.Utilities;

import com.e_commerce_backend.userservice.DTOs.UserSessionRequestDTO;
import com.e_commerce_backend.userservice.DTOs.UserSessionResponseDTO;
import com.e_commerce_backend.userservice.Models.User;
import com.e_commerce_backend.userservice.Models.UserSession;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserSessionUtility {
    public static ResponseEntity<UserSessionResponseDTO> convertResponseDTOToResponseEntity(UserSessionResponseDTO responseDTO){
        String errorMessage = responseDTO.getErrorMessage();
        if (errorMessage.isBlank())
            return new ResponseEntity<>(responseDTO, HttpStatusCode.valueOf(200));
        else if (errorMessage.equals("Server Error"))
            return new ResponseEntity<>(responseDTO,HttpStatusCode.valueOf(500));
        return new ResponseEntity<>(responseDTO,HttpStatusCode.valueOf(400));
    }

    public static String allRequiredFieldsPresentToCreateSession(UserSessionRequestDTO requestDTO){
        if (!isUserNameValid(requestDTO.getUserName()))
            return "Please enter a valid user name.";
        else if (!isPasswordValid(requestDTO.getPassword()))
            return "Please enter a valid password.";
        return "Required Fields Exist.";
    }

    public static String allRequiredFieldsPresentToTerminateSession(UserSessionRequestDTO requestDTO){
        if (!isUserNameValid(requestDTO.getUserName()))
            return "Please enter a valid session Id.";
        return "Required Fields Exist.";
    }

    public static boolean isSessionIdValid(String sessionId){
        return !sessionId.isBlank();
    }

    public static boolean isUserNameValid(String userName){
        return !userName.isBlank();
    }

    public static boolean isPasswordValid(String password){
        return !password.isBlank();
    }

    public static UserSessionResponseDTO checkIfRequiredFieldsPresentToCreateSession(UserSessionRequestDTO requestDTO){
        UserSessionResponseDTO responseDTO = new UserSessionResponseDTO();
        String requiredFieldsExist = allRequiredFieldsPresentToCreateSession(requestDTO);
        if (!requiredFieldsExist.equals("Required Fields Exist.")){
            responseDTO.setErrorMessage(requiredFieldsExist);
        }
        return responseDTO;
    }

    public static UserSessionResponseDTO checkIfRequiredFieldsPresentToTerminateSession(UserSessionRequestDTO requestDTO){
        UserSessionResponseDTO responseDTO = new UserSessionResponseDTO();
        String requiredFieldsExist = allRequiredFieldsPresentToTerminateSession(requestDTO);
        if (!requiredFieldsExist.equals("Required Fields Exist.")){
            responseDTO.setErrorMessage(requiredFieldsExist);
        }
        return responseDTO;
    }

    public static UserSessionResponseDTO checkIfUserExists(Optional<User> optionalUser){
        UserSessionResponseDTO responseDTO = new UserSessionResponseDTO();
        if (optionalUser.isEmpty()){
            responseDTO.setErrorMessage("No user with this user name exists.");
        }
        return responseDTO;
    }

    public static UserSessionResponseDTO checkIfUserHasLessThanTwoActiveSessions(Optional<List<UserSession>> optionalUserSessions){
        UserSessionResponseDTO responseDTO = new UserSessionResponseDTO();
        if (optionalUserSessions.isEmpty() || optionalUserSessions.get().size() < 2)
            return responseDTO;
        responseDTO.setErrorMessage("Two sessions are active for this user. Please log out of at least 1 of the active sessions.");
        return responseDTO;
    }

    public static void setCreateAudits(UserSession userSession){
        userSession.setCreatedBy("System");
        userSession.setCreatedDate(new Date());
        userSession.setStartedAt(new Date());
        setUpdateAudits(userSession);
    }

    public static void setUpdateAudits(UserSession userSession){
        userSession.setLastModifiedBy("System");
        userSession.setLastModifiedAt(new Date());
    }

    public static void setIsActiveFlag(UserSession userSession, boolean flag){
        userSession.setActive(flag);
        if (!flag){
            userSession.setEndedAt(new Date());
        }
    }

    public static UserSessionResponseDTO convertUserSessionToResponseDTO(UserSession userSession){
        UserSessionResponseDTO responseDTO = new UserSessionResponseDTO();
        responseDTO.setUserId(userSession.getUser().getId());
        responseDTO.setSessionToken(userSession.getSessionToken());
        responseDTO.setActive(userSession.isActive());
        return responseDTO;
    }

    public static UserSessionResponseDTO checkIfUserSessionForTokenExist(Optional<UserSession> optionalUserSession){
        UserSessionResponseDTO responseDTO = new UserSessionResponseDTO();
        if (optionalUserSession.isEmpty()){
            responseDTO.setErrorMessage("No session with given token present");
        }
        return responseDTO;
    }

}
