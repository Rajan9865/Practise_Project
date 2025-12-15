package com.practice.oms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rajan kumar
 * @version 1.0
 * Practise_Project
 * @since 12/12/2025
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

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name ", nullable = false)
    private String lastName;

    @Column(nullable = false,unique = true)
    private String email;

}
