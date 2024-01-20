package com.e_commerce_backend.userservice.Repositories;

import com.e_commerce_backend.userservice.Models.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserSessionRepository extends JpaRepository<UserSession, String> {
    @Query(nativeQuery = true, value = "SELECT * from userdb.UserSessions where user_Id = ?1 and is_active = 'TRUE' ")
    public Optional<List<UserSession>> getActiveUserSessions(String userId);

    @Query(nativeQuery = true, value = "SELECT * from userdb.UserSessions where sessionToken = ?1")
    public Optional<UserSession> getUserSessionWithSessionId(String sessionToken);

    @Query(nativeQuery = true, value = "SELECT * from user_sessions where user_id in (:userIds)")
    public Optional<List<UserSession>> getAllUserSessionsForUsers(@Param("userIds") List<String> userIds);
}
