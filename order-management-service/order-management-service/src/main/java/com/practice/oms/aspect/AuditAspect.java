package com.practice.oms.aspect;

import com.practice.oms.entity.AuditLog;
import com.practice.oms.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * AuditAspect
 *
 * <p>
 * Captures CREATE / UPDATE / DELETE operations
 * on repository layer and stores audit logs.
 * </p>
 */
@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditLogRepository auditLogRepository;

    /**
     * After save (CREATE / UPDATE)
     */
    @AfterReturning(pointcut = "execution(* org.springframework.data.jpa.repository.JpaRepository.save(..))",
    returning = "entity")
    public void auditSave(Object entity) {
        AuditLog auditLog = new AuditLog();
        auditLog.setEntityName(entity.getClass().getSimpleName());
        auditLog.setAction("SAVE");
        auditLog.setAfterData(entity.toString());
        auditLog.setChangedBy("system");
        auditLog.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(auditLog);
    }

    /**
     * After delete
     */
    @After("execution(* org.springframework.data.jpa.repository.JpaRepository.delete(..))")
    public void auditDelete(Object entity) {
        AuditLog auditLog = new AuditLog();
        auditLog.setEntityName(entity.getClass().getSimpleName());
        auditLog.setAction("DELETE");
        auditLog.setAfterData(entity.toString());
        auditLog.setChangedBy("system");
        auditLog.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(auditLog);

    }
}
