/**
 * 
 */
package com.fci.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fci.sms.entity.Customer;

/**
 * com.fci.sms.repository
 *@author Rajan kumar
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
