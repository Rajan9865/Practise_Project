/**
 * 
 */
package com.fci.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fci.sms.entity.Product;

/**
 * com.fci.sms.repository
 *@author Rajan kumar
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
