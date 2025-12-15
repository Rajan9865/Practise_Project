package com.practice.oms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lenovo
 * @version 1.0
 * Practise_Project
 * @since 12/15/2025
 */

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Customer to whom this address belongs.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String pincode;

    /**
     * HOME / OFFICE
     */
    private String addressType;
}
