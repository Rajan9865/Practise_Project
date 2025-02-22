/**
 * 
 */
package com.rajan.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajan.sms.entity.Order;

/**
 * com.rajan.sms.repository
 * 
 * @author Rajan kumar
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

}
