/**
 * 
 */
package com.rajan.sms.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.mockito.junit.jupiter.MockitoExtension;

import com.rajan.sms.entity.Category;
import com.rajan.sms.entity.Product;
import com.rajan.sms.repository.CategoryRepository;
import com.rajan.sms.repository.ProductRepository;
import com.rajan.sms.service.impl.ProductServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * com.rajan.sms.service
 *@author Rajan kumar
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
 class ProductServiceTest {
	
	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private CategoryRepository categoryRepository;

	@InjectMocks
	private ProductServiceImpl productService;

	private Category category;
	
	private Product product;

	@BeforeEach
	void setup() {
		category = new Category();
		category.setName("Electronics");
//		category.setId(1l);

		product = new Product();
//		product.setId(1l);
		product.setName("Laptop");
		product.setPrice(999.00);
		product.setStock(10);
		product.setCategory(category);
	}

	@AfterEach
	void tearDown() {
		productRepository.deleteAll();
		categoryRepository.deleteAll();
		log.info("tear down methods are executed and clear the all repository level data  ");
	}
	
	@Test
	@DisplayName("givenProduct_whenValidCategoryAndProduct_thenAddProduct")
	void givenProduct_whenValidCategoryAndProduct_thenAddProduct()
	{
		//given
		given(categoryRepository.findById(category.getId())).willReturn(Optional.of(category));
		given(productRepository.save(any(Product.class))).willReturn(product);
		
		//when
		Product addedProduct = productService.addProduct(product);
		
		//then
		then(categoryRepository).should().findById(category.getId());
		then(productRepository).should().save(any(Product.class));
		assertThat(addedProduct.getName()).isEqualTo(product.getName());
		assertThat(addedProduct.getCategory().getName()).isEqualTo(product.getCategory().getName());
	}
	
	
}
