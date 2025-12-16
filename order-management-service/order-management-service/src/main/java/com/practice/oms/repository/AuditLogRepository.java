package com.practice.oms.repository;

import com.practice.oms.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for AuditLog persistence.
 */
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}