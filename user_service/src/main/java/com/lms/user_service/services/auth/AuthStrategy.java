package com.lms.user_service.services.auth;

import com.lms.user_service.dtos.AuthResponse;
import com.lms.user_service.dtos.LoginRequest;
import com.lms.user_service.dtos.SignUpRequest;

public interface AuthStrategy {
     AuthResponse login(LoginRequest loginRequest);
     AuthResponse signup(SignUpRequest signUpRequest);
}
