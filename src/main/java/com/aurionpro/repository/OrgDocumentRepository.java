package com.aurionpro.repository;

import com.aurionpro.entity.org.OrgDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrgDocumentRepository extends JpaRepository<OrgDocument, Long> {
    List<OrgDocument> findByOrganizationId(Long organizationId);
}
