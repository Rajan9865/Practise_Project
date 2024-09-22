/**
 * 
 */
package com.fci.sms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fci.sms.dto.OrderDTO;
import com.fci.sms.entity.Customer;
import com.fci.sms.entity.Order;
import com.fci.sms.entity.Product;
import com.fci.sms.exception.InsufficientStockException;
import com.fci.sms.exception.ResourceNotFoundException;
import com.fci.sms.repository.CustomerRepository;
import com.fci.sms.repository.OrderRepository;
import com.fci.sms.repository.ProductRepository;
import com.fci.sms.service.OrderService;
import com.fci.sms.service.ProductService;

/**
 * com.fci.sms.service.impl
 * 
 * @author Rajan kumar
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;

	@Override
	public Order placeOrder(OrderDTO orderDTO) {

		Customer customer = customerRepository.findById(orderDTO.getCustomerId())
				.orElseThrow(() -> new ResourceNotFoundException("customer not found with id :"+orderDTO.getCustomerId()));

		List<Product> products = productRepository.findAllById(orderDTO.getProductIds());

		// Check stock before placing the order
		for (Product product : products) {
			if (!productService.isProductInStock(product.getId(), 1)) {
				throw new InsufficientStockException("Product out of stock: " + product.getName());
			}
		}
		// Deduct stock for each product
		for (Product product : products) {
			productService.updateStock(product.getId(), 1);
		}
		double totalPrize = products.stream().mapToDouble(Product::getPrice).sum();
		Order order = new Order();
		order.setCustomerId(customer);
		order.setProducts(products);
		order.setTotalPrize(totalPrize);
		return orderRepository.save(order);
	}
	
	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order getOrderById(Long id) {
		return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
	}

	@Override
	public void deleteById(Long id) {
		if (!orderRepository.existsById(id)) {
	        throw new ResourceNotFoundException("Order with this ID " + id + " not found");
	    }
		orderRepository.deleteById(id);
	}

}
