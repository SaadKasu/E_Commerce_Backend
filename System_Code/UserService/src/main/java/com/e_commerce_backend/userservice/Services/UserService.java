package com.e_commerce_backend.userservice.Services;

import com.e_commerce_backend.userservice.Models.User;
import com.e_commerce_backend.userservice.Repositories.UserRepository;
import com.e_commerce_backend.userservice.Utilities.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> createUser(User user) {

        if (!UserUtility.checkIfRequiredFieldsExistAtUserCreation(user))
            return Optional.empty();

        Optional<List<User>> getExistingUsers = userRepository.checkIfUserExists(user.getUserName(),user.getMobileNumber(), user.getEmailAddress());

        if (!UserUtility.isUserUnique(getExistingUsers,user))
            return Optional.empty();

        UserUtility.setIsDeletedFlagOnUser(user,false);
        UserUtility.setCreateAudits(user);
        User insertedUser = userRepository.save(user);

        return Optional.of(insertedUser);
    }

    @Override
    public Optional<User> updateUser(User user) {

        if(user.getId().isEmpty())
            return Optional.empty();

        Optional<User> optionalUser = userRepository.findById(user.getId());

        if (optionalUser.isEmpty())
            return Optional.empty();
        User existingUser = optionalUser.get();
        UserUtility.updateUserDetails(existingUser, user);
        User updatedUser = userRepository.save(existingUser);

        return Optional.of(updatedUser);
    }

    @Override
    public Optional<User> deleteUser(User user) {

        if(user.getId().isEmpty())
            return Optional.empty();

        Optional<User> optionalUser = userRepository.findById(user.getId());

        if (optionalUser.isEmpty())
            return Optional.empty();
        User existingUser = optionalUser.get();

        UserUtility.setIsDeletedFlagOnUser(existingUser,true);
        User deletedUser = userRepository.save(existingUser);

        return Optional.of(deletedUser);
    }

    @Override
    public Optional<User> getUserDetails(User user) {

        if(user.getId().isEmpty())
            return Optional.empty();

        return userRepository.findById(user.getId());

    }

    @Override
    public Optional<List<User>> getAllUsers() {
        List<User> existingUsers = userRepository.findAll();
        return Optional.of(existingUsers);
    }

}
