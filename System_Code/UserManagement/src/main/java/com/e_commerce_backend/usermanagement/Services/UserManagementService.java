package com.e_commerce_backend.usermanagement.Services;

import com.e_commerce_backend.usermanagement.DTOs.UserManagementRequestDTO;
import com.e_commerce_backend.usermanagement.DTOs.UserManagementResponseDTO;
import com.e_commerce_backend.usermanagement.Models.User;
import com.e_commerce_backend.usermanagement.Models.UserSession;
import com.e_commerce_backend.usermanagement.Repositories.UserRepository;
import com.e_commerce_backend.usermanagement.Repositories.UserSessionRepository;
import com.e_commerce_backend.usermanagement.Utilities.UserManagementUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserManagementService implements IUserManagementService{

    private final UserRepository userRepository;
    private final UserSessionRepository userSessionRepository;

    @Autowired
    public UserManagementService(UserRepository userRepository, UserSessionRepository userSessionRepository){
        this.userRepository = userRepository;
        this.userSessionRepository = userSessionRepository;
    }

    @Override
    public UserManagementResponseDTO createNewUser(UserManagementRequestDTO requestDTO) {
        UserManagementResponseDTO responseDTO = checkIfUserCanBeCreated(requestDTO);
        if (!responseDTO.getErrorMessage().isBlank())
            return responseDTO;

        User user = UserManagementUtility.convertUserRequestDTOToUserObject(requestDTO);
        UserManagementUtility.setIsDeletedFlagOnUser(user,false);
        UserManagementUtility.setCreateAudits(user);
        User insertedUser = userRepository.save(user);

        return UserManagementUtility.convertUserObjectToUserResponseDTO(insertedUser);
    }

    @Override
    public UserManagementResponseDTO updateExistingUser(UserManagementRequestDTO requestDTO) {
        UserManagementResponseDTO responseDTO = conditionsSatisfiedToPerformExistingUserOperations(requestDTO);
        if (!responseDTO.getErrorMessage().isBlank())
            return responseDTO;

        User existingUser = userRepository.getReferenceById(requestDTO.getUserId());
        UserManagementUtility.updateUserDetails(existingUser, requestDTO);
        User updatedUser = userRepository.save(existingUser);

        return UserManagementUtility.convertUserObjectToUserResponseDTO(updatedUser);
    }

    @Override
    public UserManagementResponseDTO deleteExistingUser(UserManagementRequestDTO requestDTO) {
        UserManagementResponseDTO responseDTO = conditionsSatisfiedToPerformExistingUserOperations(requestDTO);
        if (!responseDTO.getErrorMessage().isBlank())
            return responseDTO;

        User existingUser = userRepository.getReferenceById(requestDTO.getUserId());
        UserManagementUtility.setIsDeletedFlagOnUser(existingUser,true);
        User deletedUser = userRepository.save(existingUser);

        return UserManagementUtility.convertUserObjectToUserResponseDTO(deletedUser);
    }

    @Override
    public UserManagementResponseDTO getUserDetails(UserManagementRequestDTO requestDTO) {
        UserManagementResponseDTO responseDTO = conditionsSatisfiedToPerformExistingUserOperations(requestDTO);
        if (!responseDTO.getErrorMessage().isBlank())
            return responseDTO;
        User existingUser = userRepository.getReferenceById(requestDTO.getUserId());

        return UserManagementUtility.convertUserObjectToUserResponseDTO(existingUser);
    }

    @Override
    public List<UserManagementResponseDTO> getAllUsers() {
        List<User> existingUsers = userRepository.findAll();
        return UserManagementUtility.convertListOfUsersToResponseDTOs(existingUsers);
    }

    private UserManagementResponseDTO conditionsSatisfiedToPerformExistingUserOperations(UserManagementRequestDTO requestDTO){
        UserManagementResponseDTO responseDTO = new UserManagementResponseDTO();
        if (requestDTO.getUserId().isBlank()){
            responseDTO.setErrorMessage("Can not perform this operation without user Id.");
            return responseDTO;
        }
        Optional<List<UserSession>> optionalUserSessions = userSessionRepository.getActiveUserSessions(responseDTO.getUserId());
        if (optionalUserSessions.isEmpty() || optionalUserSessions.get().isEmpty()){
            responseDTO.setErrorMessage("Please login first before trying to perform this operation.");
            return responseDTO;
        }
        return responseDTO;
    }

    private UserManagementResponseDTO checkIfUserCanBeCreated(UserManagementRequestDTO requestDTO){
        UserManagementResponseDTO responseDTO = new UserManagementResponseDTO();
        String requiredFieldsCheck = UserManagementUtility.checkIfRequiredFieldsExistAtUserCreation(requestDTO);
        if (!requiredFieldsCheck.equals("Fields check complete")){
            responseDTO.setErrorMessage(requiredFieldsCheck);
            return responseDTO;
        }
        Optional<List<User>> getExistingUsers = userRepository.checkIfUserExists(requestDTO.getUserName(),requestDTO.getMobileNumber(), requestDTO.getEmailAddress());
        String existingUserCheck = UserManagementUtility.checkIfUserIsUnique(getExistingUsers,requestDTO);
        if (!existingUserCheck.equals("User is unique.")){
            responseDTO.setErrorMessage(existingUserCheck);
            return responseDTO;
        }
        return responseDTO;
    }
}
