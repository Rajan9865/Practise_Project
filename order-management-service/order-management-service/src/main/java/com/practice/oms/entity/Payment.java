package com.practice.oms.entity;

import com.practice.oms.enums.PaymentMode;
import com.practice.oms.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Payment Entity
 *
 * <p>
 * This class represents payment details for an order.
 * Each order has exactly one payment record associated with it.
 * </p>
 *
 * It stores payment mode, payment status,
 * transaction reference, and payment timestamp.
 *
 * @author Rajan Kumar
 * @version 1.0
 * @since 15-12-2024
 */
@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    /**
     * Unique identifier for the payment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Order associated with this payment.
     * One order has one payment.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    /**
     * Mode of payment (e.g. UPI, CARD, CASH).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_mode", nullable = false)
    private PaymentMode paymentMode;

    /**
     * Current status of the payment (SUCCESS, FAILED, PENDING).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    /**
     * Unique transaction reference ID from payment gateway.
     */
    @Column(name = "transaction_id", unique = true)
    private String transactionId;

    /**
     * Timestamp when payment was made.
     */
    @Column(name = "payment_time", nullable = false)
    private LocalDateTime paymentTime;

    /**
     * Automatically set payment time before persisting.
     */
    @PrePersist
    protected void onCreate() {
        this.paymentTime = LocalDateTime.now();
    }
}
