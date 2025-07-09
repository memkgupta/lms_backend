package com.lms.user_service.services.auth;

import com.lms.user_service.config.UserDetailsImpl;
import com.lms.user_service.dtos.AuthResponse;
import com.lms.user_service.dtos.LoginRequest;
import com.lms.user_service.dtos.SignUpRequest;
import com.lms.user_service.entities.User;
import com.lms.user_service.exceptions.UnAuthorisedAccess;
import com.lms.user_service.exceptions.UnauthenticatedException;
import com.lms.user_service.repositories.UserRepository;
import com.lms.user_service.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
 private final UserRepository userRepository;

    public AuthResponse login(LoginRequest loginRequest, AuthStrategy strategy) {
        return strategy.login(loginRequest);
    }

    public AuthResponse signup(SignUpRequest signUpRequest, AuthStrategy authStrategy) {
       return authStrategy.signup(signUpRequest);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthenticatedException("Unauthenticated");
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userRepository.findByEmail(userDetails.getUsername());
    }
}
