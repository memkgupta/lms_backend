package com.lms.organisation_service.dtos.employee;

import com.lms.organisation_service.entities.Role;
import lombok.Data;

@Data
public class EmployeeRequestDTO {

    private String firstName;

    private String lastName;

    private String email;


    private String phone;


    private Role role;


    private Long organisationId;
}