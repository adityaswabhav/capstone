package com.aurionpro.repository.ref;

import com.aurionpro.entity.ref.RefBusinessPartyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefBusinessPartyTypeRepo extends JpaRepository<RefBusinessPartyType, Long> {
    Optional<RefBusinessPartyType> findByCodeAndActiveTrue(String code);
}
