package com.practice.oms.entity;

import com.practice.oms.enums.AddressType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Address Entity
 *
 * <p>
 * This class represents an address associated with a customer.
 * A customer can have multiple addresses such as HOME or OFFICE.
 * </p>
 *
 * @author Rajan Kumar
 * @version 1.0
 * @since 15-12-2024
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
     * Many addresses can be associated with one customer.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * Primary address line (House no, Street, Area).
     */
    @Column(name = "address_line_1", nullable = false)
    private String addressLine1;
    /**
     * Secondary address line (optional).
     */
    @Column(name = "address_line_2")
    private String addressLine2;
    /**
     * City name.
     */
    @Column(nullable = false)
    private String city;
    /**
     * State name.
     */
    @Column(nullable = false)
    private String state;
    /**
     * Postal / PIN code.
     */
    @Column(nullable = false)
    private String pincode;

    /**
     * Type of address (HOME / OFFICE).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "address_type", nullable = false)
    private AddressType addressType;
}
