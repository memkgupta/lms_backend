package com.lms.user_service.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthResponse {
    private String access_token;
    private String refresh_token;
    private String name;
    private String id;
    private String role;
    private String email;
    private String profile;
}
