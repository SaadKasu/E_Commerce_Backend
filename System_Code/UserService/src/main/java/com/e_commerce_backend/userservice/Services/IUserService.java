package com.e_commerce_backend.userservice.Services;

import com.e_commerce_backend.userservice.Models.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    public Optional<User> createUser(User user);
    public Optional<User> updateUser(User user);
    public Optional<User> deleteUser(User user);
    public Optional<User> getUserDetails(User user);
    public Optional<List<User>> getAllUsers();
}
