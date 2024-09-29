/**
 * 
 */
package com.rajan.sms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajan.sms.entity.Product;
import com.rajan.sms.exception.InsufficientStockException;
import com.rajan.sms.exception.ResourceNotFoundException;
import com.rajan.sms.repository.ProductRepository;
import com.rajan.sms.service.ProductService;

import lombok.extern.slf4j.Slf4j;

/**
 * com.rajan.sms.service.ProductServiceImpl
 * 
 * @author rajan kumar
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product addProduct(Product product) {
		log.info("Adding new product: {}", product.getName());
		Product saveProduct= productRepository.save(product);
		log.info("Product added with ID: {}", saveProduct.getId());
		return saveProduct;

	}

	@Override
	public Product updateProduct(Long id, Product product) {
		log.info("Updating product with ID: {}", id);
		Product existingProduct = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found",id));
		existingProduct.setName(product.getName());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setStock(product.getStock());
		Product updateProduct = productRepository.save(existingProduct);
		log.info("Updating product with ID: {}", id);
		return updateProduct;
	}

	@Override
	public void deleteProduct(Long id) {
		log.info("Deleting product with ID: {}", id);
		if (!productRepository.existsById(id)) {
			 log.error("Product not found with ID: {}", id);
			throw new ResourceNotFoundException("product",id);
		}
		productRepository.deleteById(id);
		log.info("Product deleted with ID: {}", id);
	}

	@Override
	public List<Product> getAllProducts() {
		log.info("Fetching all products");
		List<Product> allproducts = productRepository.findAll();
		log.info("total product fetch {}",allproducts.size());
		return allproducts;
	}

	@Override
	public Product getProductById(Long id) {
		 log.info("Fetching product with ID: {}", id);
		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: ", id));

	}

	@Override
	public void updateStock(Long productId, int quantity) {
		 log.info("Updating stock for product ID: {} by quantity: {}", productId, quantity);
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: ",productId));

		if (product.getStock() < quantity) {
			log.error("Not enough stock for product: {}", product.getName());
			throw new InsufficientStockException("Not enough stock for product: " + product.getName());
		}
		product.setStock(product.getStock() - quantity);// decrease stocks
		productRepository.save(product);
		log.info("Stock updated for product ID: {}. New stock level: {}", productId, product.getStock());
	}

	@Override
	public boolean isProductInStock(Long productId, int quantity) {
		log.info("Checking stock for product ID: {} and quantity: {}", productId, quantity);
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: ", productId));
		return product.getStock() >= quantity;

	}

}
