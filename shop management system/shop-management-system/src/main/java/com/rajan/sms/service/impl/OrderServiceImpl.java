/**
 * 
 */
package com.rajan.sms.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajan.sms.dto.OrderDTO;
import com.rajan.sms.entity.Customer;
import com.rajan.sms.entity.Order;
import com.rajan.sms.entity.Product;
import com.rajan.sms.exception.InsufficientStockException;
import com.rajan.sms.exception.ResourceNotFoundException;
import com.rajan.sms.repository.CustomerRepository;
import com.rajan.sms.repository.OrderRepository;
import com.rajan.sms.repository.ProductRepository;
import com.rajan.sms.service.OrderService;
import com.rajan.sms.service.ProductService;

import lombok.extern.slf4j.Slf4j;

/**
 * com.rajan.sms.service.impl
 * 
 * @author Rajan kumar
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;
	
	private OrderDTO convertToDto(Order order) {
		List<Long> productIds = order.getProducts().stream().map(Product::getId).collect(Collectors.toList());

		List<Integer> quantities = order.getQuantities();

		return new OrderDTO(productIds, order.getCustomer().getId(), quantities, order.getOrderDate(),
				order.getTotalPrice());
	}
	
	@Override
	public Order placeOrder(OrderDTO orderDTO) {
		log.info("Placing order for customer ID: {}", orderDTO.getCustomerId());
		Customer customer = customerRepository.findById(orderDTO.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("customer not found with id :", orderDTO.getCustomerId()));

		List<Product> products = productRepository.findAllById(orderDTO.getProductIds());

		// Check stock before placing the order
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			int requestedQuantity = orderDTO.getQuantities().get(i);
			if (!productService.isProductInStock(product.getId(), requestedQuantity)) {
				log.error("Insufficient stock for product: {}", product.getName());
				throw new InsufficientStockException("Product out of stock: " + product.getName());
			}
		}
		// Deduct stock for each product
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			int requestedQuantity = orderDTO.getQuantities().get(i);
			productService.updateStock(product.getId(), requestedQuantity);
			log.info("Updated stock for product ID: {} by quantity: {}", product.getId(), requestedQuantity);
		}

		double totalPrize = products.stream().mapToDouble(Product::getPrice).sum();
		Order order = new Order();
//		order.setCustomerId(customer);
		order.setProducts(products);
		order.setOrderDate(LocalDateTime.now());
		order.setTotalPrice(totalPrize);
		order.setCustomer(customer);
		Order saveOrder = orderRepository.save(order);
		log.info("Order placed successfully with ID: {}", saveOrder.getId());
		return saveOrder;
	}

	@Override
	public List<Order> getAllOrders() {
		log.info("Fetching all orders");
		List<Order> orders = orderRepository.findAll();
		log.info("Total orders fetched: {}", orders.size());
		return orders;
	}

	@Override
	public Order getOrderById(Long id) {
		log.info("Fetching order with ID: {}", id);
		return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found", id));
	}

	@Override
	public void deleteById(Long id) {
		log.info("Deleting order with ID: {}", id);
		if (!orderRepository.existsById(id)) {
			log.error("Order not found with ID: {}", id);
			throw new ResourceNotFoundException("Order ", id);
		}
		orderRepository.deleteById(id);
		log.info("Order deleted with ID: {}", id);
	}

	@Override
	public List<OrderDTO> getCustomerOrders(Long customersId) {
		
		List<Order> orders = orderRepository.findByCustomerId(customersId);
        return orders.stream()
                     .map(this::convertToDto)
                     .collect(Collectors.toList());
		
	}

	@Override
	public Boolean customerExists(Long customerId) {
		return customerExists(customerId);
	}

}
