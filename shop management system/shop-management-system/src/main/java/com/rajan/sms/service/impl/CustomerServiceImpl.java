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

/**
 * com.rajan.sms.service.impl
 * 
 * @author rajan kumar
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer addCustomer(CustomerDTO customerDTO) {
		Customer customer=new Customer();
		customer.setName(customerDTO.getName());
		customer.setEmail(customerDTO.getEmail());
		customer.setAddress(customerDTO.getAddress());
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Long id, CustomerDTO customerDTO) {
			Customer customer = customerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Customer not found"));
			customer.setName(customerDTO.getName());
			customer.setAddress(customerDTO.getAddress());
			customer.setEmail(customerDTO.getAddress());
			return customerRepository.save(customer);
		
	}

	@Override
	public void deleteCustomer(Long id) {
		if (!customerRepository.existsById(id)) {
	        throw new ResourceNotFoundException("Order with this ID " + id + " not found");
	    }
		customerRepository.deleteById(id);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer not found with id :"+id));
	}

}
