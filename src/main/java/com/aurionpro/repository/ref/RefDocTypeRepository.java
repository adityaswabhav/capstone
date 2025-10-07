package com.aurionpro.repository.ref;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.entity.ref.RefDocumentType;

public interface RefDocTypeRepository extends JpaRepository<RefDocumentType, Long> {
    Optional<RefDocumentType> findByCodeAndActiveTrue(String code);
    boolean existsByCode(String code);
}
