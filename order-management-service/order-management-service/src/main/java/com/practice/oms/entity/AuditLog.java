package com.practice.oms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author lenovo
 * @version 1.0
 * Practise_Project
 * @since 12/15/2025
 */
@Entity
@Table(name = "audit_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Entity name (Order, Product, etc.)
     */
    private String entityName;

    /**
     * ID of the affected entity.
     */
    private Long entityId;

    /**
     * Action performed (CREATE, UPDATE, DELETE).
     */
    private String action;

    /**
     * User who made the change.
     */
    private String changedBy;

    /**
     * JSON before change.
     */
    @Column(columnDefinition = "TEXT")
    private String beforeData;

    /**
     * JSON after change.
     */
    @Column(columnDefinition = "TEXT")
    private String afterData;

    /**
     * Timestamp of change.
     */
    private LocalDateTime timestamp = LocalDateTime.now();
}