package com.e_commerce_backend.usermanagement.DTOs;

import lombok.Data;

@Data
public class UserManagementRequestDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String userName;
    private String password;
    private String gender;
    private String emailAddress;
    private String mobileNumber;
    private String userId;
    private Integer age;
}
