/**
 * 
 */
package com.fci.sms.controller;

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

import com.fci.sms.dto.CustomerDTO;
import com.fci.sms.entity.Customer;
import com.fci.sms.exception.ResourceNotFoundException;
import com.fci.sms.service.CustomerService;

/**
 * com.fci.sms.controller
 * 
 * @author rajan kumar
 */
@RestController
@RequestMapping("api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

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
		return new ResponseEntity<String>("Customer added successfully with ID: " + customer.getId(),
				HttpStatus.CREATED);
	}

	/**
	 * Update an existing customer by ID.
	 * @author Rajan kumar
	 * @param id          ID of the customer to update.
	 * @param customerDTO Updated customer details.
	 * @return ResponseEntity containing success or error message.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
		try {
			Customer updateCustomer = customerService.updateCustomer(id, customerDTO);
			return new ResponseEntity<String>("Customer updated successfully with ID: " + updateCustomer.getId(),
					HttpStatus.OK);
		} catch (ResourceNotFoundException exception) {
			return new ResponseEntity<String>("customer with id :" + id + " not found", HttpStatus.OK);
		}
	}

	/**
	 * Get a customer by ID.
	 * @author Rajan kumar
	 * @param id ID of the customer to retrieve.
	 * @return ResponseEntity containing customer details or an error message.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
		try {
			Customer customerById = customerService.getCustomerById(id);
			return new ResponseEntity<Object>(customerById, HttpStatus.OK);
		} catch (ResourceNotFoundException exception) {
			return new ResponseEntity<Object>("Customer with ID " + id + " not found", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Get all customers.
	 * @author Rajan kumar
	 * @return ResponseEntity containing a list of all customers.
	 */
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> allCustomers = customerService.getAllCustomers();
		return new ResponseEntity<List<Customer>>(allCustomers, HttpStatus.OK);
	}

	/**
	 * Delete a customer by ID.
	 * @author Rajan kumar
	 * @param id ID of the customer to delete.
	 * @return ResponseEntity containing a success or error message.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
		try {
			customerService.deleteCustomer(id);
			return new ResponseEntity<>("Customer deleted successfully with this ID: " + id, HttpStatus.OK);
		} catch (ResourceNotFoundException exception) {
			return new ResponseEntity<>("Customer with this ID " + id + " not found", HttpStatus.NOT_FOUND);
		}
	}
}
