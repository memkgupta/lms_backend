package com.lms.organisation_service.services;

import com.lms.organisation_service.dtos.organisation.OrganisationDTO;
import com.lms.organisation_service.entities.Employee;
import com.lms.organisation_service.entities.Organisation;
import com.lms.organisation_service.exceptions.BadRequestException;
import com.lms.organisation_service.exceptions.EntityNotFound;
import com.lms.organisation_service.repositories.EmployeeRepository;
import com.lms.organisation_service.repositories.OrganisationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.lms.organisation_service.utils.Utils.getNullOrNotPermittedPropertyNames;
import static com.lms.organisation_service.utils.Utils.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class OrgService {
    private static final Set<String> ALLOWED_UPDATE_FIELDS = Set.of(
            "name", "logo", "banner", "contactDetails"
    );

    private final OrganisationRepository organisationRepository;
    private final EmployeeService employeeService;
    public Organisation createOrganisation(Organisation organisation) {

        return organisationRepository.save(organisation);
    }

    public Organisation updateOrganisationDetails(OrganisationDTO dto, String userId) {
        Organisation organisation =getOrganisationById(dto.getId());
        if(organisation==null) throw new EntityNotFound("Organisation","id",String.valueOf(dto.getId()));
        if(dto.getAdmin()!=null)
        {

           organisation.setAdmin(employeeService.makeAdmin(dto.getId(),dto.getAdmin()));

        }
        else{
            String[] updateFields = getNullPropertyNames(dto);

            Arrays.stream(updateFields).forEach(field -> {
                if(!ALLOWED_UPDATE_FIELDS.contains(field)) {
                    throw new BadRequestException("Updating "+field+" is not allowed");
                }
            });
            BeanUtils.copyProperties(dto, organisation,updateFields );
        }


        return organisationRepository.save(organisation);
    }

    public Organisation getOrganisationById(Long organisationId) {
        Organisation organisation = organisationRepository.findById(organisationId).orElse(null);

        return organisation;
    }

    public Organisation getOrganisationByOrgCode(String orgCode) {
        Organisation organisation = organisationRepository.findByOrgCode(orgCode).orElse(null);
        if(organisation == null) {
            throw new EntityNotFound("Organisation", "orgCode", orgCode);
        }
        return organisation;
    }


    public void deleteOrganisation(Long id) {

    }

    public Page<Organisation> getAllOrgs(Pageable pageable, Specification<Organisation> spec) {
        return organisationRepository.findAll(spec,pageable);
    }
}
