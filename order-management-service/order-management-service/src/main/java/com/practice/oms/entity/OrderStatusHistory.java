package com.practice.oms.entity;

import com.practice.oms.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * OrderStatusHistory Entity
 *
 * <p>
 * This class keeps track of all status changes
 * made to an order. It helps in auditing and
 * tracking the lifecycle of an order.
 * </p>
 *
 * @author Rajan Kumar
 * @version 1.0
 * @since 15-12-2024
 */
@Entity
@Table(name = "order_status_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusHistory {

    /**
     * Unique identifier for the status history record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Order whose status has been changed.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /**
     * Previous status of the order.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "old_status", nullable = false)
    private OrderStatus oldStatus;

    /**
     * New status of the order.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "new_status", nullable = false)
    private OrderStatus newStatus;

    /**
     * User or system who changed the status.
     */
    @Column(name = "changed_by", nullable = false)
    private String changedBy;

    /**
     * Timestamp when the status was changed.
     */
    @Column(name = "changed_at", nullable = false)
    private LocalDateTime changedAt;

    /**
     * Automatically set timestamp before persisting.
     */
    @PrePersist
    protected void onCreate() {
        this.changedAt = LocalDateTime.now();
    }
}
