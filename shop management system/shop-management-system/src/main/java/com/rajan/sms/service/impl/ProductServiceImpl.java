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

/**
 * com.rajan.sms.service.ProductServiceImpl
 * 
 * @author rajan kumar
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);

	}

	@Override
	public Product updateProduct(Long id, Product product) {
		Product existingProduct = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
		existingProduct.setName(product.getName());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setStock(product.getStock());
		return productRepository.save(existingProduct);
	}

	@Override
	public void deleteProduct(Long id) {
		if (!productRepository.existsById(id)) {
			throw new ResourceNotFoundException("product with this ID " + id + " not found");
		}
		productRepository.deleteById(id);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

	}

	@Override
	public void updateStock(Long productId, int quantity) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));

		if (product.getStock() < quantity) {
			throw new InsufficientStockException("Not enough stock for product: " + product.getName());
		}
		product.setStock(product.getStock() - quantity);// decrease stocks
		productRepository.save(product);
	}

	@Override
	public boolean isProductInStock(Long productId, int quantity) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
		return product.getStock() >= quantity;

	}

}
