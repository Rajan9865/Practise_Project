package com.practice.oms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Product Entity
 *
 * <p>
 * This class represents a Product in the system.
 * It is mapped to a database table and stores
 * product-related information such as name,
 * price, and available stock.
 * </p>
 *
 * @author Rajan Kumar
 * @version 1.0
 * @since 12-12-2024
 */
@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the product.
     */
    @Column(name = "product_name", nullable = false, length = 100)
    private String productName;

    /**
     * Description of the product.
     */
    @Column(name = "product_description", length = 255)
    private String productDescription;

    /**
     * Price of the product.
     */
    @Column(name = "product_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal productPrice;

    /**
     * Available stock quantity.
     */
    @Column(name = "product_stock", nullable = false)
    private Integer productStock;
}
