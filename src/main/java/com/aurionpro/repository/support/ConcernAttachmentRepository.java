package com.aurionpro.repository.support;

import com.aurionpro.entity.support.ConcernAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcernAttachmentRepository extends JpaRepository<ConcernAttachment, Long> {
    List<ConcernAttachment> findByConcernId(Long concernId);
}
