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
@Table(name = "order_status_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    private String oldStatus;
    private String newStatus;
    private String changedBy;

    private LocalDateTime changedAt = LocalDateTime.now();
}
