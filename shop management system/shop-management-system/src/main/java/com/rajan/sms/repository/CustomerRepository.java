/**
 * 
 */
package com.rajan.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajan.sms.entity.Customer;

/**
 * com.fci.sms.repository
 *@author Rajan kumar
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}