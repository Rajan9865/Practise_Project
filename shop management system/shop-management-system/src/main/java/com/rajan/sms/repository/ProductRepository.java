/**
 * 
 */
package com.rajan.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajan.sms.entity.Product;

/**
 * com.rajan.sms.repository
 *@author Rajan kumar
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
