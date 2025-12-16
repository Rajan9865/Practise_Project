package com.practice.oms.repository;

import com.practice.oms.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CustomerRepository
 *
 * <p>
 * Repository layer responsible for
 * database operations related to Customer.
 * </p>
 */
public interface CustomerRepository
        extends JpaRepository<Customer, Long> {

    /**
     * Check if customer exists by email.
     *
     * @param email customer email
     * @return true if exists, false otherwise
     */
    boolean existsByEmail(String email);
}
