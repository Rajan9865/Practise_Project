/**
 * 
 */
package com.rajan.sms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajan.sms.dto.CustomerDTO;
import com.rajan.sms.entity.Customer;
import com.rajan.sms.exception.ResourceNotFoundException;
import com.rajan.sms.repository.CustomerRepository;
import com.rajan.sms.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

/**
 * com.rajan.sms.service.impl
 * 
 * @author rajan kumar
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer addCustomer(CustomerDTO customerDTO) {
		log.info("Adding new customer: {}", customerDTO.getName());
		Customer customer=new Customer();
		customer.setName(customerDTO.getName());
		customer.setEmail(customerDTO.getEmail());
		customer.setAddress(customerDTO.getAddress());
//		customerRepository.save(customer);
		 Customer savedCustomer = customerRepository.save(customer);
		 log.info("Customer added with ID: {}", savedCustomer.getId());
		 return savedCustomer;
	}

	@Override
	public Customer updateCustomer(Long id, CustomerDTO customerDTO) {
		log.info("Updating customer with ID: {}", id);
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found", id));
		customer.setName(customerDTO.getName());
		customer.setAddress(customerDTO.getAddress());
		customer.setEmail(customerDTO.getEmail());
		return customerRepository.save(customer);

	}

	@Override
	public void deleteCustomer(Long id) {
		log.info("Deleting customer with ID: {}", id);
		if (!customerRepository.existsById(id)) {
			log.error("Customer not found with ID: {}", id);
			throw new ResourceNotFoundException("Order with this ID " + id + " is not found", id);
		}
		customerRepository.deleteById(id);
		log.info("Customer deleted with ID: {}", id);
	}

	@Override
	public List<Customer> getAllCustomers() {
		log.info("Fetching all customers");
		List<Customer>customers=customerRepository.findAll();
		log.info("Total customers fetched: {}", customers.size());
		return customers;
	}

	@Override
	public Customer getCustomerById(Long id) {
		 log.info("Fetching customer with ID: {}", id);
		return customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found with id :", id));
	}

}
