package com.lms.user_service.controllers;

import com.lms.user_service.dtos.AuthResponse;
import com.lms.user_service.dtos.LoginRequest;
import com.lms.user_service.dtos.SignUpRequest;
import com.lms.user_service.dtos.UserInfoDTO;
import com.lms.user_service.entities.User;

import com.lms.user_service.services.auth.AuthService;
import com.lms.user_service.services.auth.EmailAuthStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final EmailAuthStrategy emailAuthStrategy;
    private final AuthService authService;

    // ✅ Signup endpoint
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignUpRequest signUpRequest) {
        
        AuthResponse response = authService.signup(signUpRequest,emailAuthStrategy);
        return ResponseEntity.ok(response);
    }

    // ✅ Login endpoint
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse response = authService.login(loginRequest,emailAuthStrategy);
        return ResponseEntity.ok(response);
    }

    // ✅ Authenticated user info
    @GetMapping("/me")
    public ResponseEntity<UserInfoDTO> getMe() {
        User user = authService.getCurrentUser();
        return ResponseEntity.ok(UserInfoDTO.builder()
                        .email(user.getEmail())
                        .name(user.getName())
                        .phone(user.getPhoneNo())
                        .username(user.getUsername())
                .build());
    }
}