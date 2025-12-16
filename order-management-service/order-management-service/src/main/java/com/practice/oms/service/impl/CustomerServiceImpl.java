package com.practice.oms.service.impl;

import com.practice.oms.customexception.ResourceNotFoundException;
import com.practice.oms.entity.Customer;
import com.practice.oms.repository.CustomerRepository;
import com.practice.oms.customexception.BusinessException;
import com.practice.oms.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CustomerServiceImpl
 *
 * <p>
 * Implementation of CustomerService interface.
 * Contains all business logic related to customers.
 * </p>
 *  * @author lenovo
 *  * @version 1.0
 *  * Practise_Project
 *  * @since 12/15/2025
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    /**
     * Repository for database operations.
     */
    private final CustomerRepository customerRepository;

    /**
     * Create customer with business validation.
     */
    @Override
    public Customer createCustomer(Customer customer) {

        // Business rule: email must be unique
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new BusinessException("Customer email already exists");
        }

        return customerRepository.save(customer);
    }

    /**
     * Get customer by ID.
     */
    @Override
    public Customer getCustomerById(Long id) {

        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found with id " + id));
    }

    /**
     * Get all customers.
     */
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Update customer details.
     */
    @Override
    public Customer updateCustomer(Long id, Customer customer) {

        Customer existingCustomer = getCustomerById(id);

        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setEmail(customer.getEmail());

        return customerRepository.save(existingCustomer);
    }

    /**
     * Delete customer.
     */
    @Override
    public void deleteCustomer(Long id) {

        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }
}
