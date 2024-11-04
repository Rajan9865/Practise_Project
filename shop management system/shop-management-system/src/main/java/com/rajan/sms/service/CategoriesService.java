/**
 * 
 */
package com.rajan.sms.service;

import java.util.List;

import com.rajan.sms.entity.Category;

/**
 * com.rajan.sms.service
 * 
 * @author Rajan kumar
 */
public interface CategoriesService {

	Category createCategory(String name);

	List<Category> getAllCategories();

	Category getCategoryById(Long id);

	Category updateCategory(Long id, String name);

	void deleteCategory(Long id);
}
