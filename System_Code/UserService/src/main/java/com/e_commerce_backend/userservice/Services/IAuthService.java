package com.e_commerce_backend.userservice.Services;

import com.e_commerce_backend.userservice.Models.Session;

import java.util.Optional;

public interface IAuthService {
    public Optional<Session> createAuthToken(String userName, String password);

    public Optional<Session> validateAuthToken(String tokenId, String userId);
}
