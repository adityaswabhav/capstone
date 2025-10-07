package com.aurionpro.repository.ref;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.entity.ref.RefBusinessPartyType;

public interface RefBusinessPartyTypeRepo extends JpaRepository<RefBusinessPartyType, Long> {
    Optional<RefBusinessPartyType> findByCodeAndActiveTrue(String code);
}
