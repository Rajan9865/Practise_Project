/**
 * 
 */
package com.fci.sms.service;

import java.util.List;

import com.fci.sms.dto.OrderDTO;
import com.fci.sms.entity.Order;

/**
 * com.fci.sms.service
 * 
 * @author Rajan kumar
 */
public interface OrderService {
	
	Order placeOrder(OrderDTO orderDTO);

	List<Order> getAllOrders();

	Order getOrderById(Long id);

	void deleteById(Long id);
}
