/**
 * 
 */
package com.rajan.sms.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.rajan.sms.entity.Category;
import com.rajan.sms.repository.CategoryRepository;


/**
 * com.fci.sms.repository
 *@author Rajan kumar
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	 void testSaveCategory()
	{
		Category category=new Category();
		
		category.setName("grocerry");
		Category save = categoryRepository.save(category);
		assertThat(save.getId()).isNotNull();
		assertThat(save.getName()).isEqualTo("grocerry");
		System.out.println("test cases passed");
	}
}
