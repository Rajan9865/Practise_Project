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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.junit.jupiter.MockitoExtension;

import com.rajan.sms.entity.Category;
import com.rajan.sms.entity.Product;
import com.rajan.sms.exception.CategoryNotFoundException;
import com.rajan.sms.exception.ResourceNotFoundException;
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
		category.setId(1l);

		product = new Product();
		product.setId(1l);
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
		log.info("Starting test: givenProduct_whenValidCategoryAndProduct_thenAddProduct");
		given(categoryRepository.findById(category.getId())).willReturn(Optional.of(category));
		given(productRepository.save(any(Product.class))).willReturn(product);
		
		//when
		 log.info("Calling productService.addProduct with product: {}", product);
		Product addedProduct = productService.addProduct(product);
		
		//then
		log.info("Verifying repository interactions");
		then(categoryRepository).should().findById(category.getId());
		then(productRepository).should().save(any(Product.class));
		assertThat(addedProduct.getName()).isEqualTo(product.getName());
		assertThat(addedProduct.getCategory().getName()).isEqualTo(product.getCategory().getName());
		log.info("Test completed successfully");
	}
	
	@Test
	@DisplayName("givenProduct_whenProductWithNonExistentCategory_thenThrowException")
	void givenProduct_whenProductWithNonExistentCategory_thenThrowException()
	{
		log.info("Starting test: givenProduct_whenProductWithNonExistentCategory_thenThrowException");
	    given(categoryRepository.findById(anyLong())).willReturn(Optional.empty());
	    log.info("Calling productService.addProduct with product: {}", product);
	    assertThrows(CategoryNotFoundException.class, () -> productService.addProduct(product));
	    

	    // Verify interactions
	    log.info("Verifying repository interactions");
	    then(categoryRepository).should().findById(anyLong());
	    then(productRepository).shouldHaveNoInteractions();
	    log.info("Test completed successfully");
	}
	
	@Test
	@DisplayName("givenProduct_whenProductExists_thenUpdateProductAndCategory")
	void givenProduct_whenProductExists_thenUpdateProductAndCategory()
	{
		//given
		log.info("Starting test: givenProduct_whenProductExists_thenUpdateProductAndCategory");
		long productId=10l;
		long newCategoryId=20l;
		category.setId(newCategoryId);
		product.setId(productId);
		
		given(productRepository.findById(productId)).willReturn(Optional.of(product));
		given(categoryRepository.findById(newCategoryId)).willReturn(Optional.of(category));
		given(productRepository.save(product)).willReturn(product);
		
		//when
		log.info("Updating product with ID: {} and setting category with ID: {}", productId, newCategoryId);
		product.setCategory(category);
		Product updateProduct = productService.updateProduct(productId, product);
		
		//then
		log.info("Verifying repository interactions");
		then(productRepository).should().findById(productId);
		then(categoryRepository).should().findById(newCategoryId);
		then(productRepository).should().save(product);
		assertThat(updateProduct.getCategory().getName()).isEqualTo(product.getCategory().getName());
		log.info("Test completed successfully");
	}
	
	@Test
	@DisplayName("givenProduct_whenProductExists_thenDeleteProduct")
	void givenProduct_whenProductExists_thenDeleteProduct() {

		// given
		log.info("Starting test: givenProduct_whenProductExists_thenDeleteProduct");
		given(productRepository.existsById(product.getId())).willReturn(true);
		willDoNothing().given(productRepository).deleteById(product.getId());

		// when
		productService.deleteProduct(product.getId());

		// then
		log.info("Verifying repository interactions");
		then(productRepository).should().deleteById(product.getId());
		log.info("Test completed successfully");
	}
	
	@Test
	@DisplayName("givenProduct_whenProductDoesNotExists_thenThrowExceptions")
	void givenProduct_whenProductDoesNotExists_thenThrowExceptions()
	{
		//given
		log.info("Starting test: givenProduct_whenProductDoesNotExists_thenThrowExceptions");
		given(productRepository.existsById(product.getId())).willReturn(false);
		
		//when/then
		log.info("Calling productService.deleteProduct with product ID: {}", product.getId());
		assertThrows(ResourceNotFoundException.class, ()->productService.deleteProduct(product.getId()));
		log.info("Test completed successfully");
	}
	
	@Test
	@DisplayName("givenProduct_whenProductExists_thenReturnAllProducts")
	void givenProduct_whenProductExists_thenReturnAllProducts()
	{
		//given
		log.info("Starting test: givenProduct_whenProductExists_thenReturnAllProducts");
		Product product2 = new Product();
        product2.setName("Phone");
        product2.setPrice(10.0);
        product2.setStock(10);
        Category category2=new Category();
        category2.setName("grocerry");
        product2.setCategory(category2);
        List<Product> products= Arrays.asList(product,product2);
        given(productRepository.findAll()).willReturn(products);
        
        //when
        List<Product> allProducts = productService.getAllProducts();
        
        //then
        log.info("Verifying repository interactions");
        then(productRepository).should().findAll();
        assertThat(allProducts).hasSize(2);
        assertThat(allProducts).containsExactlyInAnyOrder(product2,product);
        log.info("Test completed successfully");
	}
	@Test
	@DisplayName("givenProduct_whenNoProductExists_thenReturnEmptyList")
	void givenProduct_whenNoProductExists_thenReturnEmptyList() {
		//given
		log.info("starting etst : givenProduct_whenNoProductExists_thenReturnEmptyList");
		 log.info("Starting test: givenProduct_whenNoProductExists_thenReturnEmptyList");
		given(productRepository.findAll()).willReturn(List.of());
		
		//when
		List<Product> allProducts = productService.getAllProducts();
		
		//then
		then(productRepository).should().findAll();
		assertThat(allProducts).isEmpty();
		log.info("Test completed successfully");
	}
	
	@Test
	@DisplayName("givenProduct_whenExistsProduct_thenReturnProductById")
	void givenProduct_whenExistsProduct_thenReturnProductById()
	{
		//given
		log.info("starting test::givenProduct_whenExistsProduct_thenReturnProductById");
		given(productRepository.findById(product.getId())).willReturn(Optional.of(product));
		
		//when
		Product foundProduct = productService.getProductById(product.getId());
		
		//then
		log.info("Verifying repository interactions");
		then(productRepository).should().findById(product.getId());
		assertThat(foundProduct.getCategory().getName()).isEqualTo(category.getName());
		assertThat(foundProduct.getId()).isEqualTo(product.getId());
		assertThat(foundProduct.getName()).isEqualTo(product.getName());
		assertThat(foundProduct.getPrice()).isEqualTo(product.getPrice());
		assertThat(foundProduct.getStock()).isEqualTo(product.getStock());
		log.info("Test completed successfully");
	}
	
	@Test
	@DisplayName("givenProduct_whneProductNotFoundById_thenThrowException")
	void givenProduct_whneProductNotFoundById_thenThrowException()
	{
		//given
		log.info("starting test::givenProduct_whneProductNotFoundById_thenThrowException");
		given(productRepository.findById(product.getId())).willReturn(Optional.empty());
		
		//when/then
		assertThrows(ResourceNotFoundException.class, ()->productService.getProductById(product.getId()));
		then(productRepository).should().findById(product.getId());
		log.info("Test completed successfully");
	}
	
	@Test
	@DisplayName("givenProduct_whenUpdatingStockForNonExistentProduct_thenThrowException")
	void givenProduct_whenUpdatingStockForNonExistentProduct_thenThrowException()
	{
		//given
		log.info("starting test ::givenProduct_whenUpdatingStockForNonExistentProduct_thenThrowException");
		int additionalStock = 5;
		given(productRepository.findById(product.getId())).willReturn(Optional.empty());
		
		//when/then
		assertThrows(ResourceNotFoundException.class, ()->productService.updateStock(product.getId(), additionalStock));
		then(productRepository).should().findById(product.getId());
		log.info("Test completed successfully");
	}
	
	@Test
	@DisplayName("givenProduct_whenProductIsInStocks_thenReturnTrue")
	void givenProduct_whenProductIsInStocks_thenReturnTrue()
	{
		//given
		log.info("starting test :givenProduct_whenProductIsInStocks_thenReturnTrue");
		int requiredQuantity=5;
		given(productRepository.findById(product.getId())).willReturn(Optional.of(product));
		
		//when
		boolean productInStock = productService.isProductInStock(product.getId(), requiredQuantity);
		
		//then
		then(productRepository).should().findById(product.getId());
		assertThat(productInStock).isTrue();
		log.info("Test completed successfully");
	}
	
	@Test
	@DisplayName("givenProduct_whenProductIsNotInStock_thenReturnFalse")
	void givenProduct_whenProductIsNotInStock_thenReturnFalse()
	{
		//given
		log.info("staring test :givenProduct_whenProductIsNotInStock_thenReturnFalse");
		int requiredQuantity=11;
		given(productRepository.findById(product.getId())).willReturn(Optional.of(product));
		
		//when
		boolean productInStock = productService.isProductInStock(product.getId(), requiredQuantity);
		
		//then
		then(productRepository).should().findById(product.getId());
		assertThat(productInStock).isFalse();
		log.info("Test completed successfully");
	}
	
	@Test
	@DisplayName("givenProduct_whenCheckingStockForNonExistentProduct_thenThrowException")
	void givenProduct_whenCheckingStockForNonExistentProduct_thenThrowException()
	{
		//given
		log.info("Starting test: givenProduct_whenCheckingStockForNonExistentProduct_thenThrowException");
		int requiredQuantity=10;
		given(productRepository.findById(product.getId())).willReturn(Optional.empty());
		
		//when/then
		assertThrows(ResourceNotFoundException.class, ()->productService.isProductInStock(product.getId(), requiredQuantity));
		then(productRepository).should().findById(product.getId());
		log.info("Test completed successfully");
	}
}
