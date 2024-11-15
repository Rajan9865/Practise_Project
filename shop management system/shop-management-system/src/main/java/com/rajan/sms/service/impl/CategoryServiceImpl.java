/**
 * 
 */
package com.rajan.sms.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajan.sms.entity.Category;
import com.rajan.sms.exception.CategoryNotFoundException;
import com.rajan.sms.exception.InvalidCategoryException;
import com.rajan.sms.repository.CategoryRepository;
import com.rajan.sms.service.CategoriesService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.websocket.server.ServerEndpoint;

/**
 * com.rajan.sms.service.impl
 * 
 * @author Rajan kumar
 */
@Service
public class CategoryServiceImpl implements CategoriesService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category createCategory(String name) {
		if (name == null || name.isEmpty()) {
			throw new InvalidCategoryException("Category name cannot be null or empty.");
		}
		Category category = new Category();
		category.setName(name);
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

//	@Override
//	public Optional<Category> getCategoryById(Long id) {
//
//		return categoryRepository.findById(id).or(() -> {
//			throw new CategoryNotFoundException("Category not found with id " + id);
//		});
//	}

	@Override
	public Category updateCategory(Long id, String name) {
		if (name == null || name.isEmpty()) {
			throw new InvalidCategoryException("Category name cannot be null or empty.");
		}
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));
		category.setName(name);
		return categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(Long id) {
		if (!categoryRepository.existsById(id)) {
			throw new CategoryNotFoundException("Category not found with id " + id);
		}
		categoryRepository.deleteById(id);
	}

	@Override
	public Category getCategoryById(Long id) {
		return categoryRepository.findById(id).orElseThrow(()-> 
		new CategoryNotFoundException("categories not find with this id :"+id));

	}

}
