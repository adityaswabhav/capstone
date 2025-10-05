package com.aurionpro.repository.finance;

import com.aurionpro.entity.enums.PaymentRequestStatus;
import com.aurionpro.entity.finance.PaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long> {
    List<PaymentRequest> findByStatus(PaymentRequestStatus status);
    List<PaymentRequest> findByOrganizationId(Long organizationId);
    List<PaymentRequest> findByOrganizationIdAndStatus(Long organizationId, PaymentRequestStatus status);

    // Convenience overload so existing service code that passes String still compiles
    default List<PaymentRequest> findByOrganizationIdAndStatus(Long organizationId, String status) {
        return findByOrganizationIdAndStatus(organizationId, PaymentRequestStatus.valueOf(status));
    }
}
