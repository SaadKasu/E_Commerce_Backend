package com.e_commerce_backend.userservice.Repositories;

import com.e_commerce_backend.userservice.Models.Session;
import com.e_commerce_backend.userservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, String> {
    @Query(nativeQuery = true, value = "SELECT * from userdb.sessions where token = ?1 and user_id = ?2 and ends_At > NOW()")
    public Optional<Session> findSessionByTokenAndUser(String token, String userId);

    @Query(nativeQuery = true, value = "SELECT * from userdb.sessions where user_id = ?1 and ends_At > NOW()")
    public Optional<List<Session>> findActiveSessionsForUser(String userId);
}
