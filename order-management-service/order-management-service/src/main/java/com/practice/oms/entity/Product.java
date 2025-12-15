package com.practice.oms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long productId;

    private String productName;

    private String productDescription;

    private BigDecimal productPrice;

    private Integer productStock;
}
