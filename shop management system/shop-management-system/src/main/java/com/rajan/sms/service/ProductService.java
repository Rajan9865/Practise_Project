/**
 * 
 */
package com.rajan.sms.service;

import java.util.List;

import com.rajan.sms.entity.Product;

/**
 * com.rajan.sms.service
 * 
 * @author Rajan kumar
 */
public interface ProductService {

	// for add the product
	Product addProduct(Product product);

	// for update the product items
	Product updateProduct(Long id, Product product);

	// for delete the product items
	void deleteProduct(Long id);

	// get all the products
	List<Product> getAllProducts();

	// get single product fetch
	Product getProductById(Long id);

	void updateStock(Long productId, int quantity);

	boolean isProductInStock(Long productId, int quantity);
}
