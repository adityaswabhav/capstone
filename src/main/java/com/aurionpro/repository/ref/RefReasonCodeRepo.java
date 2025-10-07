package com.aurionpro.repository.ref;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.entity.ref.RefReasonCode;

public interface RefReasonCodeRepo extends JpaRepository<RefReasonCode, Long> {
    Optional<RefReasonCode> findByCodeAndActiveTrue(String code);
}
