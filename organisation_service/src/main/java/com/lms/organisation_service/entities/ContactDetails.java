package com.lms.organisation_service.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ContactDetails {
    private String email;
    private String phone;
    private String address;
}
