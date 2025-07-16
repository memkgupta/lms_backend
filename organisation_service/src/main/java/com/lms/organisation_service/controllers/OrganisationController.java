package com.lms.organisation_service.controllers;


import com.lms.organisation_service.dtos.PaginatedResponse;
import com.lms.organisation_service.dtos.organisation.OrganisationDTO;
import com.lms.organisation_service.entities.Employee;
import com.lms.organisation_service.entities.Organisation;

import com.lms.organisation_service.services.OrgService;
import com.lms.organisation_service.utils.PaginationUtil;
import com.lms.organisation_service.utils.specifications.generics.SpecificationStrategyFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/org")
@RequiredArgsConstructor
public class OrganisationController {

    private final OrgService organisationService;
    private final SpecificationStrategyFactory<Organisation> specFactory;
    @PostMapping
    public ResponseEntity<Organisation> createOrganisation(@RequestBody OrganisationDTO organisationDTO, HttpServletRequest request) {
        Organisation organisation = new Organisation();
        organisation.setName(organisationDTO.getName());
        organisation.setOrgCode(organisationDTO.getOrgCode());
        String userId = request.getHeader("X-USER-ID");
        organisation.setOwnerId(userId);
        Organisation saved = organisationService.createOrganisation(organisation);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organisation> getOrganisationById(@PathVariable Long id) {
        Organisation org = organisationService.getOrganisationById(id);
        return ResponseEntity.ok(org);
    }

    @PutMapping
    public ResponseEntity<Organisation> updateOrganisation(
            @RequestBody OrganisationDTO organisationDto,
            @RequestHeader("X-User-Id") String userId) {
        Organisation updated = organisationService.updateOrganisationDetails(organisationDto, userId);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrganisation(@PathVariable Long id) {
        organisationService.deleteOrganisation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<Organisation>> getAllOrganisations(@RequestParam Map<String, String> filters) {
        Specification<Organisation> spec = null;

        int page = Integer.parseInt(filters.get("page"));
        int size = Integer.parseInt(filters.get("size"));
        filters.remove("page");
        filters.remove("size");
        Pageable pageable = PageRequest.of(page, size);
        for (var entry : filters.entrySet()) {
            Specification<Organisation> current = specFactory.getSpecification(entry.getKey(), entry.getValue());
            if (current != null) {
                spec = (spec == null) ? current : spec.and(current);
            }
        }
        return ResponseEntity.ok(PaginationUtil.toPaginatedResponse(organisationService.getAllOrgs(pageable,spec)));
    }
}
