/**
 * 
 */
package com.rajan.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rajan.sms.entity.Order;

/**
 * com.rajan.sms.repository
 *@author Rajan kumar
 */
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	List<Order> findByCustomerId(Long customerId);
//	@Query("SELECT o FROM Order o WHERE o.customer.id = :customerId")
//    List<Order> findByCustomerId(@Param("customerId") Long customerId);

}
