package com.lms.organisation_service.repositories;

import com.lms.organisation_service.entities.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface OrganisationRepository extends JpaRepository<Organisation, Long>, JpaSpecificationExecutor<Organisation> {
    Optional<Organisation> findByOrgCode(String name);
}
