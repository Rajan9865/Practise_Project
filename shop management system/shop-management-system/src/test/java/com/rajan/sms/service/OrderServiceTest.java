/**
 * 
 */
package com.rajan.sms.service;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rajan.sms.entity.Category;
import com.rajan.sms.entity.Customer;
import com.rajan.sms.entity.Order;
import com.rajan.sms.entity.Product;
import com.rajan.sms.repository.CategoryRepository;
import com.rajan.sms.repository.CustomerRepository;
import com.rajan.sms.repository.OrderRepository;
import com.rajan.sms.repository.ProductRepository;
import com.rajan.sms.service.impl.OrderServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * com.rajan.sms.service
 * 
 * @author Rajan kumar
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
	
	@Mock
	private OrderRepository orderRepository;
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private CategoryRepository categoryRepository;
	
	@InjectMocks
	private OrderServiceImpl orderService;
	
	private Customer customer;
	
	private Product product;
	
	private Order order;
	
	private Category category;
	
	@BeforeEach
	void setup()
	{
		customer=new Customer();
        customer.setName("Rajan");
        customer.setAddress("Patna");
        customer.setEmail("rajan@gmail.com");
        
        category = new Category();
		category.setName("Electronics");

		product =new Product();
        product.setName("Laptop");
        product.setPrice(1000.0);
        product.setStock(5);
        product.setCategory(category);
		
		order = new Order();
        order.setCustomerId(customer);
        order.setProducts(Arrays.asList(product));
        order.setTotalPrize(1500.0);
	}
	
	@AfterEach
	void tearDown()
	{
		customerRepository.deleteAll();
		categoryRepository.deleteAll();
		productRepository.deleteAll();
		orderRepository.deleteAll();
		log.info("tear down methods are executed and clear the all repository level data  ");
	}
	
	
}
