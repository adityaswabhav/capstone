package com.aurionpro.repository;

import com.aurionpro.entity.enums.OrganizationStatus;
import com.aurionpro.entity.org.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    boolean existsByNameIgnoreCase(String name);
    List<Organization> findByStatus(OrganizationStatus status);
}
