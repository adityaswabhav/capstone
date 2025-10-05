package com.aurionpro.repository.bank;

import com.aurionpro.entity.bank.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Long> {

    // Return the first bank row as the "default" (works even if no isDefault flag exists)
    @Query(value = "SELECT * FROM bank ORDER BY id ASC LIMIT 1", nativeQuery = true)
    Optional<Bank> findDefaultBank();
}
