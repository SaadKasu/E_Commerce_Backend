package com.e_commerce_backend.userservice.Utilities;

import com.e_commerce_backend.userservice.DTOs.AuthRequestDTO;
import com.e_commerce_backend.userservice.DTOs.AuthResponseDTO;
import com.e_commerce_backend.userservice.Models.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.text.DateFormat;
import java.util.Date;
import java.util.Optional;


public class AuthUtility {
    public static Session createSessionForUser(User user){
        Session session = new Session();
        session.setUser(user);
        session.setToken(RandomStringUtils.randomAlphanumeric(30).toUpperCase());
        long futureTime = System.currentTimeMillis() + (86400 * 7 * 1000);
        session.setEnds_At(new Date(futureTime));
        return session;
    }

    public static void setCreateAutdits (Session session){
        session.setCreatedBy("System");
        session.setCreatedDate(new Date());
        setLastModifiedAudits(session);
    }

    public static void setLastModifiedAudits(Session session){
        session.setLastModifiedBy("System");
        session.setLastModifiedAt(new Date());
    }

    public static AuthResponseDTO convertSessionToResponseDTO(Optional<Session> optionalSession){
        AuthResponseDTO responseDTO = new AuthResponseDTO();
        if (optionalSession.isEmpty()){
            responseDTO.setErrorMessage("Session Details Not Valid, Please check the details");
            return responseDTO;
        }
        Session session = optionalSession.get();
        responseDTO.setUserId(session.getId());
        responseDTO.setEndsAt(session.getEnds_At());
        responseDTO.setToken(session.getToken());
        return responseDTO;
    }

    public static ResponseEntity<AuthResponseDTO> convertResponseDTOToEntity(AuthResponseDTO responseDTO){
        if (responseDTO.getErrorMessage() != null && !responseDTO.getErrorMessage().isBlank()){
            return new ResponseEntity<>(responseDTO, HttpStatusCode.valueOf(400));
        }
        MultiValueMap<String,String> headerValue = new LinkedMultiValueMap<>();
        headerValue.add("Token", responseDTO.getToken());
        return new ResponseEntity<>(responseDTO,headerValue,HttpStatusCode.valueOf(200));
    }

}
