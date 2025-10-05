package com.aurionpro.repository;

import com.aurionpro.entity.employee.BankAccountUpdateRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccUpdateReqRepository extends JpaRepository<BankAccountUpdateRequest, Long> {
}
