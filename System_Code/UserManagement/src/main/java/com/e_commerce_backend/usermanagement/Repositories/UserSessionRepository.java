package com.e_commerce_backend.usermanagement.Repositories;

import com.e_commerce_backend.usermanagement.Models.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserSessionRepository extends JpaRepository<UserSession, String> {
    @Query(nativeQuery = true, value = "SELECT * from userdb.UserSessions where userId = ?1 and isActive = true")
    public Optional<List<UserSession>> getActiveUserSessions(String userId);

    @Query(nativeQuery = true, value = "SELECT * from userdb.UserSessions where sessionToken = ?1")
    public Optional<UserSession> getUserSessionWithSessionId(String sessionToken);
}
