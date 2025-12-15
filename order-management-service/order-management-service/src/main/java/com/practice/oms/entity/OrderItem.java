package com.practice.oms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * OrderItem Entity
 *
 * <p>
 * This class represents an individual item within an order.
 * It links an Order with a Product and stores quantity
 * and price details at the time of ordering.
 * </p>
 *
 * @author Rajan Kumar
 * @version 1.0
 * @since 15-12-2024
 */
@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    /**
     * Unique identifier for the order item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Parent order to which this item belongs.
     * Many order items can belong to one order.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /**
     * Product associated with this order item.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


    /**
     * Quantity of the product ordered.
     */
    @Column(nullable = false)
    private Integer quantity;

    /**
     * Price of the product at the time the order was placed.
     * Stored separately to preserve historical pricing.
     */
    @Column(name = "price_at_order_time", nullable = false)
    private BigDecimal priceAtOrderTime;
}
