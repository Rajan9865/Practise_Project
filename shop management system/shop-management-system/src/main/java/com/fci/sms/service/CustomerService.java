/**
 * 
 */
package com.fci.sms.service;

import java.util.List;

import com.fci.sms.dto.CustomerDTO;
import com.fci.sms.entity.Customer;

/**
 * com.fci.sms.service
 *@author Rajan kumar
 */
public interface CustomerService {
	
	Customer addCustomer(CustomerDTO customerDTO);
	
	Customer updateCustomer(Long id,CustomerDTO customerDTO);
	
	void deleteCustomer(Long id);
	
	List<Customer>getAllCustomers();
	
	Customer getCustomerById(Long id);
}
