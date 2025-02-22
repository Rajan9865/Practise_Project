/**
 * 
 */
package com.rajan.sms.service;

import java.util.List;

import com.rajan.sms.dto.OrderDTO;
import com.rajan.sms.entity.Order;

/**
 * com.rajan.sms.service
 * 
 * @author Rajan kumar
 */
public interface OrderService {

	Order placeOrder(OrderDTO orderDTO);

	List<Order> getAllOrders();

	Order getOrderById(Long id);

	void deleteById(Long id);
}
