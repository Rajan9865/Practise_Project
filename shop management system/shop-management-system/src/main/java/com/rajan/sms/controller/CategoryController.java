/**
 * 
 */
package com.rajan.sms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajan.sms.entity.Category;
import com.rajan.sms.service.CategoriesService;

import lombok.extern.slf4j.Slf4j;

/**
 * com.rajan.sms.controller
 * 
 * @author Rajan kumar
 */
@Slf4j
@RestController
@RequestMapping("/api/categories/")
public class CategoryController {

	@Autowired
	private CategoriesService categoriesService;

	@PostMapping
	public ResponseEntity<String> createCategory(@RequestBody Map<String, String> request) {
		Category category = categoriesService.createCategory(request.get("name"));
		log.info("Category created successfully with ID: {}", category.getId());
		return new ResponseEntity<>("Customer added successfully with this ID: " + category.getId(),
				HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Category>> getAllCategories() {
		log.info("fetsching all category ");
		List<Category> allCategories = categoriesService.getAllCategories();
		log.info("received {} categories ", allCategories.size());
		return new ResponseEntity<>(allCategories, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
		log.info("fetching category with this id {}", id);
		Category categoryById = categoriesService.getCategoryById(id);
		log.info("retrived category {}", categoryById);
		return new ResponseEntity<>(categoryById, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody Category category) {
		log.info("updating category with this id {}", id);
		Category updateCategory = categoriesService.updateCategory(id, category.getName());
		log.info("updated catefory : {}", updateCategory);
		return new ResponseEntity<>("Cetegory successfully updated with this id " + updateCategory.getId(),
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
		log.info("deleting category with this id {}", id);
		categoriesService.deleteCategory(id);
		log.info("category deleted with this id {}", id);
		return new ResponseEntity<>("category deleted successfully with this id :" + id, HttpStatus.OK);
	}

}
