/**
 * 
 */
package com.rajan.sms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rajan.sms.entity.Category;
import com.rajan.sms.exception.CategoryNotFoundException;
import com.rajan.sms.repository.CategoryRepository;
import com.rajan.sms.service.impl.CategoryServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * com.rajan.sms.service
 *@author Rajan kumar
 */
@ExtendWith(MockitoExtension.class)
@Slf4j
 class CategoriesServiceTest {

	@Mock
	private CategoryRepository categoryRepository;
	
	@InjectMocks
	private CategoryServiceImpl categoryServiceImpl;
	

	private Category category;

	@BeforeEach
	void setUp() {
		category = new Category();
		category.setId(1l);
		category.setName("Electronics");
	}

	@AfterEach
	public void tearDown() {
		categoryRepository.deleteAll();
		log.info("Tear down methods executed and repository cleared after each test.");
	}
	
	
	@Test
	@DisplayName("givenCategoryName_whenCreateCategory_thenCategoryIsCreated")
	void givenCategoryName_whenCreateCategory_thenCategoryIsCreated()
	{
		//given
		given(categoryRepository.save(any(Category.class))).willReturn(category);
		
		//when
		Category createCategory = categoryServiceImpl.createCategory(category.getName());
		log.info(" category name : {}",createCategory.getName());
		
		//then
		assertNotNull(createCategory);
		assertEquals(category.getName(), createCategory.getName());
		then(categoryRepository).should().save(any(Category.class));
		assertThat(createCategory.getName()).isEqualTo(category.getName());
		log.info(" create category junit test case ");
	}

	@Test
	@DisplayName("givenCategoriesExist_whenGetAllCategories_thenReturnAllCategories")
	void givenCategoriesExist_whenGetAllCategories_thenReturnAllCategories()
	{
		//given
		Category category1=new Category();
		category1.setName("Grocerry");
		List<Category> categories = Arrays.asList(category,category1);
		given(categoryRepository.findAll()).willReturn(categories);
		
		//when
		List<Category> allCategories = categoryServiceImpl.getAllCategories();
		log.info("size of all categories {}",allCategories.size());
		//then
		then(categoryRepository).should().findAll();
		assertThat(allCategories).hasSize(2);
		assertThat(allCategories).contains(category,category1);
		log.info("All category founded  {}",allCategories.size());
	}
	
	@Test
	@DisplayName("givenCategoryId_whenGetCategoryById_thenReturnCategory")
	void givenCategoryId_whenGetCategoryById_thenReturnCategory()
	{
		//given
		given(categoryRepository.findById(category.getId())).willReturn(Optional.of(category));
		
		//when
		log.info("fetching category by ID : {}",category.getId());
		Category categoryById = categoryServiceImpl.getCategoryById(category.getId());
		
		//then
		log.info("verifying that findByID was called with ID : {}",category.getId());
		then(categoryRepository).should().findById(category.getId());
		assertThat(categoryById.getId()).isEqualTo(1l);
		assertThat(categoryById.getName()).isEqualTo(category.getName());
		log.info("Category fetch successfully {}",categoryById);
	}
	
	
	@Test
	@DisplayName("givenNonExistentCategoryId_whenGetCategoryById_thenThrowException")
	void givenNonExistentCategoryId_whenGetCategoryById_thenThrowException() {
		// given
		given(categoryRepository.findById(category.getId())).willReturn(Optional.empty());

		// when/then
		assertThrows(CategoryNotFoundException.class, () -> categoryServiceImpl.getCategoryById(category.getId()));
		then(categoryRepository).should().findById(category.getId());
		log.info("find category category By Id ");
	}
	
	@Test
	@DisplayName("givenCategoryIdAndUpdateName_whenUpdateCategory_thenCategoryIsUpdated")
	void givenCategoryIdAndUpdateName_whenUpdateCategory_thenCategoryIsUpdated() {
		// given
		String updatedName = "updated Electronics";
		given(categoryRepository.findById(category.getId())).willReturn(Optional.of(category));
		given(categoryRepository.save(category)).willReturn(category);

		// when
		Category updateCategory = categoryServiceImpl.updateCategory(category.getId(), updatedName);

		// then
		then(categoryRepository).should().findById(category.getId());
		then(categoryRepository).should().save(category);
		assertThat(updateCategory.getName()).isEqualTo(updatedName);
	}
	
	@Test
	@DisplayName("givenCategoryId_whenDeleteCategory_thenCategoryIsDeleted")
	void givenCategoryId_whenDeleteCategory_thenCategoryIsDeleted() {
		// given
		given(categoryRepository.findById(category.getId())).willReturn(Optional.of(category));
		log.info("category id  ::{}", category.getId());
		willDoNothing().given(categoryRepository).deleteById(category.getId());

		log.info("category id  {}", category.getId());
		// when
		categoryServiceImpl.deleteCategory(category.getId());

		// then
		then(categoryRepository).should().deleteById(category.getId());
	}
	
	@Test
	@DisplayName("givenNonExistentCategoryId_whenDeleteCategory_thenThrowException")
	void givenNonExistentCategoryId_whenDeleteCategory_thenThrowException()
	{
		willThrow(new CategoryNotFoundException("category not found")).given(categoryRepository).deleteById(category.getId());
		
		//will/then
		assertThrows(CategoryNotFoundException.class, ()->categoryServiceImpl.deleteCategory(category.getId()));
		then(categoryRepository).should().deleteById(category.getId());
	}
}
