package com.practice.oms.entity;

import com.practice.oms.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Shipment Entity
 *
 * <p>
 * This class represents shipment and delivery details
 * for an order. It tracks courier information,
 * shipment status, and delivery timestamps.
 * </p>
 *
 * @author Rajan Kumar
 * @version 1.0
 * @since 15-12-2024
 */
@Entity
@Table(name = "shipments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {

    /**
     * Unique identifier for the shipment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Order associated with this shipment.
     * One order has one shipment.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    /**
     * Name of the courier service.
     */
    @Column(name = "courier_name", nullable = false)
    private String courierName;

    /**
     * Tracking number provided by the courier.
     */
    @Column(name = "tracking_number", unique = true)
    private String trackingNumber;

    /**
     * Current shipment status.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "shipment_status", nullable = false)
    private ShipmentStatus shipmentStatus;

    /**
     * Timestamp when the order was shipped.
     */
    @Column(name = "shipped_at")
    private LocalDateTime shippedAt;

    /**
     * Timestamp when the order was delivered.
     */
    @Column(name = "delivered_at")
    private LocalDateTime deliveredAt;

    /**
     * Automatically set shipped time before persisting.
     */
    @PrePersist
    protected void onCreate() {
        this.shippedAt = LocalDateTime.now();
    }
}
