package com.e_commerce_backend.userservice.Utilities;

import com.e_commerce_backend.userservice.DTOs.UserRequestDTO;
import com.e_commerce_backend.userservice.DTOs.UserResponseDTO;
import com.e_commerce_backend.userservice.Models.Gender;
import com.e_commerce_backend.userservice.Models.User;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class UserUtility {
    public static User convertUserRequestDTOToUserObject(UserRequestDTO requestDTO){

        User userObj = new User();
        Gender gender = convertStringToGender(requestDTO.getGender());
        userObj.setUserName(requestDTO.getUserName());
        userObj.setAge(requestDTO.getAge());
        userObj.setGender(gender);
        userObj.setFirstName(requestDTO.getFirstName());
        userObj.setLastName(requestDTO.getLastName());
        userObj.setEmailAddress(requestDTO.getEmailAddress());
        userObj.setMiddleName(requestDTO.getMiddleName());
        userObj.setMobileNumber(requestDTO.getMobileNumber());
        userObj.setPassword(Base64.getEncoder().encodeToString(requestDTO.getPassword().getBytes()));
        userObj.setId(requestDTO.getUserId());

        return userObj;
    }

    public static UserResponseDTO convertUserObjectToUserResponseDTO(Optional<User> optionalUser){

        UserResponseDTO responseDTO = new UserResponseDTO();
        if (optionalUser.isEmpty()){
            responseDTO.setErrorMessage("Something Went Wrong Please Check the Request Payload");
            return responseDTO;
        }
        User user = optionalUser.get();

        String stringGender = getGenderAsString(user.getGender());
        responseDTO.setUserName(user.getUserName());
        responseDTO.setAge(user.getAge());
        responseDTO.setGender(stringGender);
        responseDTO.setFirstName(user.getFirstName());
        responseDTO.setEmailAddress(user.getEmailAddress());
        responseDTO.setLastName(user.getLastName());
        responseDTO.setMiddleName(user.getMiddleName());
        responseDTO.setMobileNumber(user.getMobileNumber());
        responseDTO.setUserId(user.getId());
        responseDTO.setDeleted(user.isDeleted());

        return responseDTO;
    }

    public static String getGenderAsString(Gender gender){
        return gender == null ? null : (gender == Gender.MALE ? "Male" : (gender == Gender.FEMALE ? "Female" : "Other"));
    }

    public static Gender convertStringToGender(String stringGender){
        if (stringGender == null || stringGender.isBlank())
            return null;
        stringGender = stringGender.toLowerCase();
        return stringGender.equals("male") ? Gender.MALE : (stringGender.equals("female") ? Gender.FEMALE : Gender.OTHER);
    }

    public static ResponseEntity<UserResponseDTO> convertResponseDTOToResponseEntity(UserResponseDTO responseDTO){
        String errorMessage = responseDTO.getErrorMessage();
        if (errorMessage == null || errorMessage.isBlank())
            return new ResponseEntity<>(responseDTO, HttpStatusCode.valueOf(200));
        else if (errorMessage.equals("Server Error"))
            return new ResponseEntity<>(responseDTO,HttpStatusCode.valueOf(500));
        return new ResponseEntity<>(responseDTO,HttpStatusCode.valueOf(400));
    }

    public static ResponseEntity<List<UserResponseDTO>> convertListOfReponseDTOsToResponseEntity(List<UserResponseDTO> responseDTOS){
        if (responseDTOS == null || responseDTOS.isEmpty()){
            responseDTOS = new ArrayList<>();
            UserResponseDTO responseDTO = new UserResponseDTO();
            responseDTO.setErrorMessage("Server Error");
            responseDTOS.add(responseDTO);
            return new ResponseEntity<>(responseDTOS,HttpStatusCode.valueOf(500));
        }
        else if (responseDTOS.size() == 1 && !responseDTOS.get(0).getErrorMessage().isBlank()){
            UserResponseDTO responseDTO = responseDTOS.get(0);
            if (responseDTO.getErrorMessage().equals("Server Error"))
                return new ResponseEntity<>(responseDTOS,HttpStatusCode.valueOf(500));
            return new ResponseEntity<>(responseDTOS,HttpStatusCode.valueOf(400));
        }
        return new ResponseEntity<>(responseDTOS,HttpStatusCode.valueOf(200));
    }

    public static List<UserResponseDTO> convertListOfUsersToResponseDTOs(Optional<List<User>> optionalUsers){
        List<UserResponseDTO> responseDTOS = new ArrayList<>();
        if (optionalUsers.isEmpty()){
            UserResponseDTO responseDTO = new UserResponseDTO();
            responseDTO.setErrorMessage("No users found");
            responseDTOS.add(responseDTO);
        }
        else{
            for (User user : optionalUsers.get())
                responseDTOS.add(convertUserObjectToUserResponseDTO(Optional.of(user)));
        }
        return responseDTOS;
    }

    public static UserRequestDTO createUserRequestDTOWithUserId(String userId){
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setUserId(userId);
        return requestDTO;
    }

    public static boolean isUserUnique(Optional<List<User>> optionalUsers, User user){
        if (optionalUsers.isEmpty() || optionalUsers.get().isEmpty())
            return true;
        List<User> existingUsers = optionalUsers.get();
        System.out.println("New User Name - "+ user.getUserName() + " New User Number - "+user.getMobileNumber() + " New User Email - "+ user.getEmailAddress());
        for(User existingUser : existingUsers){
            System.out.println("Existing User Name - "+ existingUser.getUserName() + " Existing User Number - "+existingUser.getMobileNumber() + " Existing User Email - "+ existingUser.getEmailAddress());
            if (existingUser.getUserName().equalsIgnoreCase(user.getUserName()))
                return false;
            if (existingUser.getEmailAddress().equalsIgnoreCase(user.getEmailAddress()))
                return false;
            if (existingUser.getMobileNumber().equalsIgnoreCase(user.getMobileNumber()))
                return false;
        }

        return true;
    }

    public static boolean checkIfRequiredFieldsExistAtUserCreation(User user){
        if (!isUserNameValid(user.getUserName()))
            return false;
        else if (!isFirstNameValid(user.getFirstName()))
            return false;
        else if (!isLastNameValid(user.getLastName()))
            return false;
        else if (!isMobileNumberValid(user.getMobileNumber()))
            return false;
        else if (!isEmailAddressValid(user.getEmailAddress()))
            return false;
        else if (!isAgeValid(user.getAge()))
            return false;
        else if (!isPasswordValid(user.getPassword()))
            return false;
        return true;
    }

    public static boolean isPasswordValid(String password){
        if (password == null || password.isBlank())
            return false;
        return true;
    }
    public static boolean isUserNameValid(String userName){
        if (userName == null || userName.isBlank())
            return false;
        return true;
    }

    public static boolean isFirstNameValid(String firstName){
        if (firstName == null || firstName.isBlank())
            return false;
        return true;
    }

    public static boolean isMiddleNameValid(String middleName){
        if (middleName == null || middleName.isBlank())
            return false;
        return true;
    }

    public static boolean isLastNameValid(String lastName){
        if (lastName == null || lastName.isBlank())
            return false;
        return true;
    }

    public static boolean isMobileNumberValid(String mobileNumber){
        if (mobileNumber == null || mobileNumber.isBlank())
            return false;
        return true;
    }

    public static boolean isEmailAddressValid(String emailAddress){
        if (emailAddress == null || emailAddress.isBlank())
            return false;
        return true;
    }

    public static boolean isAgeValid(Integer age){
        if (age == null || age < 0 || age > 100)
            return false;
        return true;
    }

    public static void setOnCreateAudits(User user){
        user.setCreatedBy("System");
        user.setLastModifiedBy("System");
        user.setCreatedDate(new Date());
        user.setLastModifiedAt(new Date());
    }

    public static void setOnUpdateAudits(User user){
        user.setLastModifiedBy("System");
        user.setLastModifiedAt(new Date());
    }

    public static void setIsDeletedFlagOnUser(User user, boolean value){
        user.setDeleted(value);
    }

    public static void updateUserDetails(User existingUser, User newUser){
        if (isAgeValid(newUser.getAge()))
            existingUser.setAge(newUser.getAge());
        if (!(newUser.getGender() ==null))
            existingUser.setGender(newUser.getGender());
        if (isMiddleNameValid(newUser.getMiddleName()))
            existingUser.setMiddleName(newUser.getMiddleName());
        if (isMobileNumberValid(newUser.getMobileNumber()))
            existingUser.setMobileNumber(newUser.getMobileNumber());
        if (isEmailAddressValid(newUser.getEmailAddress()))
            existingUser.setEmailAddress(newUser.getEmailAddress());
        if (isLastNameValid(newUser.getLastName()))
            existingUser.setLastName(newUser.getLastName());
        if (isPasswordValid(newUser.getPassword()))
            existingUser.setPassword(newUser.getPassword());
        if (isFirstNameValid(newUser.getFirstName()))
            existingUser.setFirstName(newUser.getFirstName());
    }

}
