package com.e_commerce_backend.userservice.Repositories;

import com.e_commerce_backend.userservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    @Query(nativeQuery = true, value = "SELECT * from userdb.users where userName = ?1 or mobileNumber =?2 or emailAddress =?3")
    public Optional<List<User>> checkIfUserExists(String userName, String mobileNumber, String emailAddress);

    @Query(nativeQuery = true, value = "SELECT * from userdb.users where userName = ?1")
    public Optional<User> checkIfUserWithUserNameExists(String userName);
}
