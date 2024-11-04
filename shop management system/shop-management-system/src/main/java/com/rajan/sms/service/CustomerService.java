/**
 * 
 */
package com.rajan.sms.service;

import java.util.List;

import com.rajan.sms.dto.CustomerDTO;
import com.rajan.sms.entity.Customer;

/**
 * com.rajan.sms.service
 *@author Rajan kumar
 */
public interface CustomerService {
	
	Customer addCustomer(CustomerDTO customerDTO);
	
	Customer updateCustomer(Long id,CustomerDTO customerDTO);
	
	void deleteCustomer(Long id);
	
	List<Customer>getAllCustomers();
	
	Customer getCustomerById(Long id);
	
}
