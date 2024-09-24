/**
 * 
 */
package com.rajan.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rajan.sms.dto.OrderDTO;
import com.rajan.sms.entity.Order;
import com.rajan.sms.exception.InvalidRequestException;
import com.rajan.sms.exception.ResourceNotFoundException;
import com.rajan.sms.service.OrderService;

import jakarta.validation.Valid;

/**
 * com.rajan.sms.controller
 * 
 * @author Rajan kumar
 */
@Controller
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * Place a new order.
	 * 
	 * @author Rajan kumar
	 * @param orderDTO Order data transfer object containing product IDs and other
	 *                 order details.
	 * @return ResponseEntity containing success message and the order ID.
	 */
	@PostMapping
	public ResponseEntity<String> placeOrder(@Valid @RequestBody OrderDTO orderDTO) {
		if (orderDTO.getProductIds() == null || orderDTO.getProductIds().isEmpty()) {
			throw new InvalidRequestException("No products selected for the order.");
		}

		Order placeOrder = orderService.placeOrder(orderDTO);
		return new ResponseEntity<>("Order placed successfully with ID: " + placeOrder.getId(), HttpStatus.CREATED);
	}

	/**
	 * Get an order by ID.
	 * 
	 * @author Rajan kumar
	 * @param id ID of the order to retrieve.
	 * @return ResponseEntity containing the order details or an error message.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOrderById(@PathVariable Long id) {
		try {
			Order orderById = orderService.getOrderById(id);
			return new ResponseEntity<>(orderById, HttpStatus.OK);
		} catch (ResourceNotFoundException exception) {
			return new ResponseEntity<Object>("order with id " + id + " not Found", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Get all orders.
	 * 
	 * @author Rajan kumar
	 * @return ResponseEntity containing a list of all orders.
	 */
	@GetMapping
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> allOrders = orderService.getAllOrders();
		return new ResponseEntity<>(allOrders, HttpStatus.OK);
	}

	/**
	 * Delete an order by ID.
	 * 
	 * @author Rajan kumar
	 * @param id ID of the order to delete.
	 * @return ResponseEntity containing a success or error message.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
		try {
			orderService.deleteById(id);
			return new ResponseEntity<>(" order deleted successfully with this id " + id, HttpStatus.OK);
		} catch (ResourceNotFoundException exception) {
			return new ResponseEntity<>("order with this id " + id + " not found", HttpStatus.NOT_FOUND);
		}
	}
}
