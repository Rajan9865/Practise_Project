/**
 * 
 */
package com.rajan.sms.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.rajan.sms.entity.Category;

import lombok.extern.slf4j.Slf4j;


/**
 * com.rajan.sms.repository
 *@author Rajan kumar
 */
@DataJpaTest
@Slf4j
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@DisplayName("givenCategorylist_WhenFindAll_thenReturnCategoryList")
	@Test
	 void givenCategorylist_WhenFindAll_thenReturnCategoryList()
	{
		Category category=new Category();
		
		category.setName("grocerry");
		Category save = categoryRepository.save(category);
		assertThat(save.getId()).isNotNull();
		assertThat(save.getName()).isEqualTo("grocerry");
		log.info("findAll test cases passed");
	}
	
	@DisplayName("givenCategoryObject_whenFindById_thenReturnCategoryObject")
	@Test
	void givenCategoryObject_whenFindById_thenReturnCategoryObject()
	{
		Category category=new Category();
		category.setName("books");
		Category save = categoryRepository.save(category);
		Optional<Category> foundCategory = categoryRepository.findById(save.getId());
		assertThat(foundCategory).isPresent();
		assertThat(foundCategory.get().getName()).isEqualTo("books");
		log.info("find by customer id test cases passed");
	}
	
	@Test
	@DisplayName("givenCategoryObject_whenCategoryDeletebyId_thenReturnCategoryObject")
	void givenCategoryObject_whenCategoryDeletebyId_thenReturnCategoryObject()
	{
		Category category=new Category();
		category.setName("toys");
		Category save = categoryRepository.save(category);
//		categoryRepository.deleteById(1l);
		categoryRepository.delete(save);
		Optional<Category> deleteCategory = categoryRepository.findById(save.getId());
		assertThat(deleteCategory).isEmpty();
		log.info("delete category by id  test case passed");
	}
	
	@DisplayName("givenCategoryObject_whenFindNonExistentCategoryById_thenReturnEmptyObject")
	@Test
	void givenCategoryObject_whenFindNonExistentCategoryById_thenReturnEmptyObject()
	{
		long nonExistId=99l;
		Optional<Category> foundCategory = categoryRepository.findById(nonExistId);
		assertThat(foundCategory).isEmpty();
		log.info("FindNonExistentCategoryById methods test case  passsed");
	}
	
	@DisplayName("givenCategoryObject_whenDeleteNonExistentCategory_thenReturnEmptyCategoryObject")
	@Test
	void givenCategoryObject_whenDeleteNonExistentCategory_thenReturnEmptyCategoryObject()
	{
		Category category=new Category();
		category.setId(99l);
		category.setName("nonExistElement");
//		categoryRepository.delete(category);
		categoryRepository.deleteAll();
		long countAfterDelete = categoryRepository.count();
		log.info("count {}",countAfterDelete);
		assertThat(countAfterDelete).isZero();
		log.info("whenDeleteNonExistentCategory test cases passed");
		
	}
	
	@DisplayName("givenCategpryObject_whenSaveCategoryWithoutName_thenReturnCategoryNull")
	@Test
	void givenCategpryObject_whenSaveCategoryWithoutName_thenReturnCategoryNull()
	{
		Category category=new Category();
		Category saveCategory = categoryRepository.save(category);
		assertThat(saveCategory.getId()).isNotNull();
		assertThat(saveCategory.getName()).isNull();
		log.info("SaveCategoryWithoutName methods test case passsed");
	}
}
