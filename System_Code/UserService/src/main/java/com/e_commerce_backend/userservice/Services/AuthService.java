package com.e_commerce_backend.userservice.Services;

import com.e_commerce_backend.userservice.Models.Session;
import com.e_commerce_backend.userservice.Models.User;
import com.e_commerce_backend.userservice.Repositories.SessionRepository;
import com.e_commerce_backend.userservice.Utilities.AuthUtility;
import com.e_commerce_backend.userservice.Utilities.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService implements IAuthService{

    private final SessionRepository sessionRepository;
    private final IUserService userService;

    @Autowired
    AuthService(SessionRepository sessionRepository, UserService userService){
        this.userService = userService;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Optional<Session> createAuthToken(String userName, String password) {
        User userToLogin = UserUtility.getUserFromUserNameAndPassword(userName,password);
        Optional<User> optionalUser = userService.loginUser(userToLogin);

        if (optionalUser.isEmpty())
            return Optional.empty();

        User existingUser = optionalUser.get();

        if (moreThanPermittedSessionsActiveForUser(existingUser))
            return Optional.empty();

        Session session = AuthUtility.createSessionForUser(existingUser);
        AuthUtility.setCreateAutdits(session);

        session = sessionRepository.save(session);

        return Optional.of(session);

    }

    @Override
    public Optional<Session> validateAuthToken(String tokenId, String userId) {

        return sessionRepository.findSessionByTokenAndUser(tokenId,userId);
    }

    private boolean moreThanPermittedSessionsActiveForUser(User user){

        Optional<List<Session>> activeSessionsForUser = sessionRepository.findActiveSessionsForUser(user.getId());

        return activeSessionsForUser.isEmpty() || activeSessionsForUser.get().size() < 2 ;
    }
}
