package com.e_commerce_backend.userservice.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
@Entity(name = "UserSessions")
public class UserSession extends BaseModel{
    @GeneratedValue(strategy =GenerationType.UUID)
    private String sessionToken;
    @Column(nullable = false)
    private Date startedAt;
    private Date endedAt;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;
    private boolean isActive;
}
