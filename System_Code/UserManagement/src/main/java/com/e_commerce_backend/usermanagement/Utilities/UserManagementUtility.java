package com.e_commerce_backend.usermanagement.Utilities;

import com.e_commerce_backend.usermanagement.DTOs.UserManagementRequestDTO;
import com.e_commerce_backend.usermanagement.DTOs.UserManagementResponseDTO;
import com.e_commerce_backend.usermanagement.Models.Gender;
import com.e_commerce_backend.usermanagement.Models.User;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserManagementUtility {
    public static User convertUserRequestDTOToUserObject(UserManagementRequestDTO requestDTO){

        String stringGender = requestDTO.getGender().toLowerCase();
        Gender gender = stringGender.equals("male") ? Gender.MALE : (stringGender.equals("female") ? Gender.FEMALE : Gender.OTHER);

        User userObj = new User();
        userObj.setUserName(requestDTO.getUserName());
        userObj.setAge(requestDTO.getAge());
        userObj.setGender(gender);
        userObj.setFirstName(requestDTO.getFirstName());
        userObj.setLastName(requestDTO.getLastName());
        userObj.setEmailAddress(requestDTO.getEmailAddress());
        userObj.setMiddleName(requestDTO.getMiddleName());
        userObj.setMobileNumber(requestDTO.getMobileNumber());
        userObj.setPassword(requestDTO.getPassword());

        return userObj;
    }

    public static UserManagementResponseDTO convertUserObjectToUserResponseDTO(User user){
        String stringGender = getGenderAsString(user.getGender());

        UserManagementResponseDTO responseDTO = new UserManagementResponseDTO();

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
        String stringGender = gender == Gender.MALE ? "Male" : (gender == Gender.FEMALE ? "Female" : "Other");
        return stringGender;
    }

    public static Gender convertStringToGender(String stringGender){
        stringGender = stringGender.toLowerCase();
        Gender gender = stringGender.equals("male") ? Gender.MALE : (stringGender.equals("female") ? Gender.FEMALE : Gender.OTHER);
        return gender;
    }

    public static ResponseEntity<UserManagementResponseDTO> convertResponseDTOToResponseEntity(UserManagementResponseDTO responseDTO){
        String errorMessage = responseDTO.getErrorMessage();
        if (errorMessage.isBlank())
            return new ResponseEntity<>(responseDTO, HttpStatusCode.valueOf(200));
        else if (errorMessage.equals("Server Error"))
            return new ResponseEntity<>(responseDTO,HttpStatusCode.valueOf(500));
        return new ResponseEntity<>(responseDTO,HttpStatusCode.valueOf(400));
    }

    public static ResponseEntity<List<UserManagementResponseDTO>> convertListOfReponseDTOsToResponseEntity(List<UserManagementResponseDTO> responseDTOS){
        if (responseDTOS == null || responseDTOS.isEmpty()){
            responseDTOS = new ArrayList<>();
            UserManagementResponseDTO responseDTO = new UserManagementResponseDTO();
            responseDTO.setErrorMessage("Server Error");
            responseDTOS.add(responseDTO);
            return new ResponseEntity<>(responseDTOS,HttpStatusCode.valueOf(500));
        }
        else if (responseDTOS.size() == 1 && !responseDTOS.get(0).getErrorMessage().isBlank()){
            UserManagementResponseDTO responseDTO = responseDTOS.get(0);
            if (responseDTO.getErrorMessage().equals("Server Error"))
                return new ResponseEntity<>(responseDTOS,HttpStatusCode.valueOf(500));
            return new ResponseEntity<>(responseDTOS,HttpStatusCode.valueOf(400));
        }
        return new ResponseEntity<>(responseDTOS,HttpStatusCode.valueOf(200));
    }

    public static List<UserManagementResponseDTO> convertListOfUsersToResponseDTOs(List<User> users){
        List<UserManagementResponseDTO> responseDTOS = new ArrayList<>();
        if (users.isEmpty()){
            UserManagementResponseDTO responseDTO = new UserManagementResponseDTO();
            responseDTO.setErrorMessage("No users found");
            responseDTOS.add(responseDTO);
        }
        for (User user : users)
            responseDTOS.add(convertUserObjectToUserResponseDTO(user));
        return responseDTOS;
    }

    public static UserManagementRequestDTO createUserManagementRequestDTOFromUserName(String userName){
        UserManagementRequestDTO requestDTO = new UserManagementRequestDTO();
        requestDTO.setUserName(userName);
        return requestDTO;
    }

    public static String checkIfUserIsUnique(Optional<List<User>> existingUsers,UserManagementRequestDTO requestDTO){
        if (existingUsers.isEmpty())
            return "User is unique.";
        List<User> users = existingUsers.get();
        if (users.isEmpty())
            return "User is unique.";
        boolean userNameMatch = false, emailAddressMatch = false, mobileNumberMatch = false;
        for(User user : users){
            if (user.getUserName().equals(requestDTO.getUserName()))
                userNameMatch = true;
            if (user.getEmailAddress().equals(requestDTO.getEmailAddress()))
                emailAddressMatch = true;
            if (user.getMobileNumber().equals(requestDTO.getMobileNumber()))
                mobileNumberMatch = true;
        }
        String errorMessage = "Please change the following field/s, they are already used in the system :";
        errorMessage += userNameMatch ? "\n User Name" : "";
        errorMessage += emailAddressMatch ? "\n Email Address": "";
        errorMessage += mobileNumberMatch ? "\n Mobile Number": "";
        return errorMessage;
    }

    public static String checkIfRequiredFieldsExistAtUserCreation(UserManagementRequestDTO requestDTO){
        if (!isUserNameValid(requestDTO.getUserName()))
            return "Please select a valid username.";
        else if (!isFirstNameValid(requestDTO.getFirstName()))
            return "Please enter a valid first name.";
        else if (!isLastNameValid(requestDTO.getLastName()))
            return "Please enter a valid last name.";
        else if (!isMobileNumberValid(requestDTO.getMobileNumber()))
            return "Please enter a valid mobile number.";
        else if (!isEmailAddressValid(requestDTO.getEmailAddress()))
            return "Please enter a valid email addresss";
        else if (!isAgeValid(requestDTO.getAge()))
            return "Please enter a valid age.";
        else if (!isPasswordValid(requestDTO.getPassword()))
            return "Please enter a valid password.";
        return "Fields check complete";
    }

    private static boolean isPasswordValid(String password){
        if (!password.isBlank())
            return false;
        return true;
    }
    public static boolean isUserNameValid(String userName){
        if (userName.isBlank())
            return false;
        return true;
    }

    public static boolean isFirstNameValid(String firstName){
        if (firstName.isBlank())
            return false;
        return true;
    }

    public static boolean isLastNameValid(String lastName){
        if (lastName.isBlank())
            return false;
        return true;
    }

    public static boolean isMobileNumberValid(String mobileNumber){
        if (mobileNumber.isBlank())
            return false;
        return true;
    }

    public static boolean isEmailAddressValid(String emailAddress){
        if (emailAddress.isBlank())
            return false;
        return true;
    }

    public static boolean isAgeValid(Integer age){
        if (age == null || age < 0 || age > 100)
            return false;
        return true;
    }

    public static void setCreateAudits(User user){
        user.setCreatedBy("System");
        user.setLastModifiedBy("System");
        user.setCreatedDate(new Date());
        user.setLastModifiedAt(new Date());
    }

    public static void setUpdateOrDeleteAudits(User user){
        user.setLastModifiedBy("System");
        user.setLastModifiedAt(new Date());
    }

    public static void setIsDeletedFlagOnUser(User user, boolean value){
        user.setDeleted(value);
    }

    public static void updateUserDetails(User user, UserManagementRequestDTO requestDTO){
        if (isAgeValid(requestDTO.getAge()))
            user.setAge(requestDTO.getAge());
        if (!requestDTO.getGender().isBlank())
            user.setGender(convertStringToGender(requestDTO.getGender()));
        if (!requestDTO.getMiddleName().isBlank())
            user.setMiddleName(requestDTO.getMiddleName());
        if (isMobileNumberValid(requestDTO.getMobileNumber()))
            user.setMobileNumber(requestDTO.getMobileNumber());
        if (isEmailAddressValid(requestDTO.getEmailAddress()))
            user.setEmailAddress(requestDTO.getEmailAddress());
        if (isLastNameValid(requestDTO.getLastName()))
            user.setLastName(requestDTO.getLastName());
        if (isPasswordValid(requestDTO.getPassword()))
            user.setPassword(requestDTO.getPassword());
        if (isFirstNameValid(requestDTO.getFirstName()))
            user.setFirstName(requestDTO.getFirstName());
    }

}
