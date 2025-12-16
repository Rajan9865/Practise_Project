package com.practice.oms.controller;

import com.practice.oms.entity.Customer;
import com.practice.oms.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CustomerController
 *
 * <p>
 * REST controller responsible for handling all customer-related
 * operations in the Order Management System (OMS).
 * </p>
 *
 * <p>
 * This controller exposes APIs for:
 * <ul>
 *   <li>Create a customer</li>
 *   <li>Fetch customer by ID</li>
 *   <li>Fetch all customers</li>
 *   <li>Update customer details</li>
 *   <li>Delete a customer</li>
 * </ul>
 * </p>
 *
 * <p>
 * Design principles followed:
 * <ul>
 *   <li>Thin controller (no business logic)</li>
 *   <li>Delegates processing to service layer</li>
 *   <li>RESTful URL and HTTP method usage</li>
 * </ul>
 * </p>
 *
 * Base URL: <b>/api/customers</b>
 *
 * @author Rajan Kumar
 * @version 1.0
 */
@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    /**
     * Service layer dependency for customer operations.
     * Constructor injection is used for immutability and testability.
     */
    private final CustomerService customerService;

    /**
     * Create a new customer.
     *
     * <p>
     * Accepts customer details in request body and persists
     * them in the database.
     * </p>
     *
     * HTTP Method: POST
     * URL: /api/customers
     *
     * @param customer Customer details to be created
     * @return Created customer with HTTP status 201 (CREATED)
     */
    @PostMapping
    public ResponseEntity<Customer> createCustomer(
            @RequestBody Customer customer) {

        Customer savedCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    /**
     * Fetch a customer by ID.
     *
     * <p>
     * Retrieves customer information using the unique customer ID.
     * </p>
     *
     * HTTP Method: GET
     * URL: /api/customers/{id}
     *
     * @param id Customer ID
     * @return Customer details if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(
            @PathVariable Long id) {

        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    /**
     * Fetch all customers.
     *
     * <p>
     * Returns a list of all customers present in the system.
     * </p>
     *
     * HTTP Method: GET
     * URL: /api/customers
     *
     * @return List of customers
     */
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {

        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    /**
     * Update customer details.
     *
     * <p>
     * Updates existing customer information based on the provided ID.
     * </p>
     *
     * HTTP Method: PUT
     * URL: /api/customers/{id}
     *
     * @param id Customer ID to update
     * @param customer Updated customer data
     * @return Updated customer information
     */
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer customer) {

        Customer updatedCustomer =
                customerService.updateCustomer(id, customer);

        return ResponseEntity.ok(updatedCustomer);
    }

    /**
     * Delete a customer.
     *
     * <p>
     * Deletes the customer identified by the given ID.
     * </p>
     *
     * HTTP Method: DELETE
     * URL: /api/customers/{id}
     *
     * @param id Customer ID to delete
     * @return Success message after deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(
            @PathVariable Long id) {

        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }
}
