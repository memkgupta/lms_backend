package com.lms.organisation_service.dtos.organisation;

import com.lms.organisation_service.dtos.ContactDetailsDTO;
import com.lms.organisation_service.dtos.employee.EmployeeDTO;
import com.lms.organisation_service.entities.ContactDetails;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrganisationDTO {
    private long id;
    private String name;
    private String description;
    private String location;
    private String logo;
    private String banner;
    private ContactDetails contactDetails;
    private boolean isVerified;
    private String orgCode;
    private Long admin;
    private String ownerId;

}
