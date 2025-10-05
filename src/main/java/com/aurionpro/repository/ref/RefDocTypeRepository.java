package com.aurionpro.repository.ref;

import com.aurionpro.entity.ref.RefDocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefDocTypeRepository extends JpaRepository<RefDocumentType, Long> {
    Optional<RefDocumentType> findByCodeAndActiveTrue(String code);
    boolean existsByCode(String code);
}
