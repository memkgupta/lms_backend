package com.lms.organisation_service.dtos.employee;


import com.lms.organisation_service.dtos.organisation.OrganisationDTO;
import com.lms.organisation_service.entities.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Role role;
    private Long organisationId;
    private OrganisationDTO organisation;
}
