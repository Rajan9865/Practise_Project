/**
 * 
 */
package com.rajan.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajan.sms.dto.CustomerDTO;
import com.rajan.sms.dto.OrderDTO;
import com.rajan.sms.entity.Customer;
import com.rajan.sms.exception.ResourceNotFoundException;
import com.rajan.sms.service.CustomerService;
import com.rajan.sms.service.OrderService;

/**
 * com.rajan.sms.controller
 * 
 * @author rajan kumar
 */
@RestController
@RequestMapping("api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderService orderService;

	/**
	 * Add a new customer to the system.
	 * 
	 * @author Rajan kumar
	 * @param customerDTO Customer data transfer object containing customer details.
	 * @return ResponseEntity containing success message and the customer ID.
	 */
	@PostMapping
	public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customerDTO) {
		Customer customer = customerService.addCustomer(customerDTO);
		return new ResponseEntity<>("Customer added successfully with ID: " + customer.getId(), HttpStatus.CREATED);
	}

	/**
	 * Update an existing customer by ID.
	 * 
	 * @author Rajan kumar
	 * @param id          ID of the customer to update.
	 * @param customerDTO Updated customer details.
	 * @return ResponseEntity containing success or error message.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
		Customer updateCustomer = customerService.updateCustomer(id, customerDTO);
		return new ResponseEntity<>("Customer updated successfully with ID: " + updateCustomer.getId(), HttpStatus.OK);

	}

	/**
	 * Get a customer by ID.
	 * 
	 * @author Rajan kumar
	 * @param id ID of the customer to retrieve.
	 * @return ResponseEntity containing customer details or an error message.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
		Customer customerById = customerService.getCustomerById(id);
		return new ResponseEntity<>(customerById, HttpStatus.OK);
	}

	/**
	 * Get all customers.
	 * 
	 * @author Rajan kumar
	 * @return ResponseEntity containing a list of all customers.
	 */
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> allCustomers = customerService.getAllCustomers();
		return new ResponseEntity<>(allCustomers, HttpStatus.OK);
	}

	/**
	 * Delete a customer by ID.
	 * 
	 * @author Rajan kumar
	 * @param id ID of the customer to delete.
	 * @return ResponseEntity containing a success or error message.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		return new ResponseEntity<String>("Customer deleted successfully with this id :" + id, HttpStatus.OK);

	}
	
	@GetMapping("/{customerId}/orders")
	public ResponseEntity<List<OrderDTO>>getCustomerOrders(@PathVariable Long customerId)
	{
		Boolean customerExists = orderService.customerExists(customerId);
		if (!customerExists) {
			throw new ResourceNotFoundException("Customer not found with this ID: ", customerId);
		}
		List<OrderDTO> customerOrders = orderService.getCustomerOrders(customerId);
		if (customerOrders.isEmpty()) {
			throw new ResourceNotFoundException("No orders found for customer with ID: ", customerId);
		}
		return new ResponseEntity<>(customerOrders, HttpStatus.OK);
		
	}
}
