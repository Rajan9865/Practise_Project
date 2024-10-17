/**
 * 
 */
package com.rajan.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rajan.sms.entity.Customer;
import com.rajan.sms.entity.Order;

/**
 * com.rajan.sms.repository
 *@author Rajan kumar
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
//	@Query(new Customer from Customer c join Order o on (c.id=o.customerId)  where id= :customerId)
//	Customer findCustomerByCustomerId(Long customerId);
}
