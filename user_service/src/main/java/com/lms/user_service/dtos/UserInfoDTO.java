package com.lms.user_service.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserInfoDTO {
    private String username;
    private String name;
    private String email;
    private String phone;
    private String role;
}
