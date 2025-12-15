package com.practice.oms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Customer Entity
 *
 * <p>
 * This class represents the Customer entity and is mapped
 * to the "customers" table in the database.
 * </p>
 *
 * <p>
 * It stores basic customer information such as first name,
 * last name, and email address.
 * </p>
 *
 * @author Rajan Kumar
 * @version 1.0
 * @since 12-12-2024
 */
@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * First name of the customer.
     * This field cannot be null.
     */
    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name ", nullable = false)
    private String lastName;

    @Column(nullable = false,unique = true)
    private String email;

}
