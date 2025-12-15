package com.practice.oms.entity;

import com.practice.oms.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Order Entity
 *
 * <p>
 * This class represents an Order placed by a customer.
 * It maintains the relationship between Customer and Product
 * and stores order-related details such as total price
 * and order status.
 * </p>
 *
 * @author Rajan Kumar
 * @version 1.0
 * @since 12-12-2024
 */
@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Customer who placed the order.
     * Many orders can belong to one customer.
     */
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * List of products included in the order.
     * One order can contain multiple products.
     */
    @ManyToMany
    @JoinTable(name = "order_products", joinColumns = @JoinColumn(name = "order_id")
            , inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    /**
     * Total price before discount.
     */
    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    /**
     * Discount applied on the order.
     */
    @Column(name = "discount_price", precision = 10, scale = 2)
    private BigDecimal discountPrice;

    /**
     * Current order status.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    /**
     * Order creation timestamp.
     */
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}
