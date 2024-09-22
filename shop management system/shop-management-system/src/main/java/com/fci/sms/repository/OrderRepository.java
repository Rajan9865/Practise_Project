/**
 * 
 */
package com.fci.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fci.sms.entity.Order;

/**
 * com.fci.sms.repository
 *@author Rajan kumar
 */
public interface OrderRepository extends JpaRepository<Order, Long>{

}
