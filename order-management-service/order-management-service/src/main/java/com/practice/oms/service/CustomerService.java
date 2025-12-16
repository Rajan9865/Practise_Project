package com.practice.oms.service;

import com.practice.oms.entity.Customer;

import java.util.List;

/**
 * CustomerService
 *
 * <p>
 * Service interface defining business operations
 * related to Customer management.
 * </p>
 *
 * <p>
 * This layer acts as a bridge between
 * Controller and Repository.
 * </p>
 * @author rajan kumar
 */
public interface CustomerService {
    /**
     * Create a new customer.
     *
     * @param customer customer details
     * @return saved customer entity
     */
    Customer createCustomer(Customer customer);

    /**
     * Fetch customer by ID.
     *
     * @param id customer ID
     * @return customer entity
     */
    Customer getCustomerById(Long id);

    /**
     * Fetch all customers.
     *
     * @return list of customers
     */
    List<Customer> getAllCustomers();

    /**
     * Update existing customer.
     *
     * @param id customer ID
     * @param customer updated customer data
     * @return updated customer entity
     */
    Customer updateCustomer(Long id, Customer customer);

    /**
     * Delete customer by ID.
     *
     * @param id customer ID
     */
    void deleteCustomer(Long id);
}
