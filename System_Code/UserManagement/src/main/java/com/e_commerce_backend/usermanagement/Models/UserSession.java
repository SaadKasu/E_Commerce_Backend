package com.e_commerce_backend.usermanagement.Models;

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
    @Column(name = "userId")
    private User user;
    private boolean isActive;
}
