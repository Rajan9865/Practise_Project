package com.practice.oms.entity;

import ch.qos.logback.classic.model.LevelModel;
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
@Table(
        name = "customers",
        uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
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
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    /**
     * Email address of the customer.
     * Must be unique.
     */
    @Column(nullable = false, unique = true, length = 100)
    private String email;

}
