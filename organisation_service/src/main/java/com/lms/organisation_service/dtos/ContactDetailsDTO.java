package com.lms.organisation_service.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContactDetailsDTO {
    private String email;
    private String phone;
    private String address;
}
