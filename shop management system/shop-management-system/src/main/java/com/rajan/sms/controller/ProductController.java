package com.rajan.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rajan.sms.entity.Product;
import com.rajan.sms.exception.InsufficientStockException;
import com.rajan.sms.exception.ResourceNotFoundException;
import com.rajan.sms.service.ProductService;

/**
 * com.rajan.sms.controller
 * 
 * @author Rajan kumar
 * 
 */
@Controller
@RequestMapping("api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * Add a new product to the system.
	 * 
	 * @param Product details to be added.
	 * @return ResponseEntity containing success message and the product ID of the
	 *         newly added product.
	 * @author Rajan kumar
	 */
	@PostMapping
	public ResponseEntity<String> addProduct(@RequestBody Product product) {
		Product newProduct = productService.addProduct(product);
		return new ResponseEntity<>("product addedd successfull with this id " + newProduct.getId(),
				HttpStatus.CREATED);
	}

	/**
	 * @author Rajan kumar Update an existing product by ID.
	 * @param id
	 * @param product Updated product details.
	 * @return ResponseEntity containing success or error message.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product) {
		Product updateProduct = productService.updateProduct(id, product);
		return new ResponseEntity<>("product updated successfully wit this id " + updateProduct.getId(), HttpStatus.OK);
	}

	/**
	 * Retrieve all products.
	 * 
	 * @author Rajan kumar
	 * @return ResponseEntity containing a list of all products.
	 */
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> allProducts = productService.getAllProducts();
		return new ResponseEntity<>(allProducts, HttpStatus.OK);
	}

	/**
	 * Delete a product by ID.
	 * 
	 * @author Rajan kumar
	 * @param id ID of the product to be deleted.
	 * @return ResponseEntity containing success or error message.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return new ResponseEntity<>("product deleted successfully with this id " + id, HttpStatus.OK);
	}

	// Update stock for a product
	/**
	 * Update stock for a specific product.
	 * 
	 * @author Rajan kumar
	 * @param id       ID of the product whose stock needs to be updated.
	 * @param quantity New stock quantity to be set.
	 * @return ResponseEntity containing success or error message.
	 */
	@PatchMapping("/{id}/stock")
	public ResponseEntity<String> updataStock(@PathVariable Long id, @RequestParam int quantity) {
		productService.updateStock(id, quantity);
		return new ResponseEntity<>("Stock updated successfully for Product ID: " + id, HttpStatus.OK);
	}

	// Check stock for a product
	/**
	 * Check if a product is in stock for a specific quantity.
	 * 
	 * @author Rajan kumar
	 * @param id
	 * @param quantity Quantity to check.
	 * @return ResponseEntity indicating whether the product is in stock.
	 */
	@GetMapping("/{id}/stock")
	public ResponseEntity<String> isProductInStock(@PathVariable Long id, @RequestParam int quantity) {
		boolean productInStock = productService.isProductInStock(id, quantity);
		return new ResponseEntity<>(productInStock ? "Product is in stock for the requested quantity"
				: "Insufficient stock for Product ID: " + id, HttpStatus.OK);
	}
}
