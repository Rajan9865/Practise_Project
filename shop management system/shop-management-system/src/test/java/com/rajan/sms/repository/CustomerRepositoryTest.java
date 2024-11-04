/**
 * 
 */
package com.rajan.sms.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.rajan.sms.entity.Customer;

import lombok.extern.slf4j.Slf4j;

/**
 * com.rajan.sms.repository
 *@author Rajan kumar
 */
@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class CustomerRepositoryTest {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	private Customer customer;

	@BeforeEach
	public void setup() {
		customer = new Customer();
		customer.setName("Rajan");
		customer.setEmail("rajan@gmail.com");
		customer.setAddress("Patna");
	}
	
	@AfterEach
	public void tearDown()
	{
  		customerRepository.deleteAll();
	    log.info("Tear down methods executed and repository cleared after each test.");
	}

	@Test
	@DisplayName("givenValidCustomer_whenSaved_theCustomerIsPersist")
	public void givenValidCustomer_whenSaved_theCustomerIsPersist() {
		Customer saveCustomer = customerRepository.save(customer);
		assertThat(saveCustomer.getId()).isNotNull();
		assertThat(saveCustomer.getName()).isEqualTo("Rajan");
		assertThat(saveCustomer.getEmail()).isEqualTo("rajan@gmail.com");
		assertThat(saveCustomer.getAddress()).isEqualTo("Patna");

		log.info("customer name :{}", saveCustomer.getName());
	}
	
	@Test
	@DisplayName("givenCustomerId_whenFindingCustomer_thenReturnCustomerIsFound")
	public void givenCustomerId_whenFindingCustomer_thenReturnCustomerIsFound()
	{
		Customer save = customerRepository.save(customer);
		Optional<Customer> foundCustomerById = customerRepository.findById(save.getId());
		assertThat(foundCustomerById).isPresent();
		assertThat(save.getName()).isEqualTo("Rajan");
		assertThat(save.getAddress()).isEqualTo("Patna");
		assertThat(save.getEmail()).isEqualTo("rajan@gmail.com");
		
		log.info("givenCustomerId_whenFindingCustomer_thenReturnCustomerIsFound test cases passed");
	}
	
	
	@Test
	@DisplayName("givenMultipleCustomers_whenfindingAll_thenReturnAllCustomeredReturned")
	public void givenMultipleCustomers_whenfindingAll_thenReturnAllCustomeredReturned()
	{
		Customer customer1=new Customer();
		customer1.setAddress("Noida");
		customer1.setEmail("rani@gmail.com");
		customer1.setName("Rani");
		
		customerRepository.save(customer1);
		customerRepository.save(customer);
		List<Customer> allCustomers = customerRepository.findAll();
		log.info("print all customer {}",allCustomers.size());
		allCustomers.forEach(c -> log.info("Customer ID: {}, Name: {}", c.getId(), c.getName()));
		assertThat(allCustomers).hasSize(2);
		assertThat(allCustomers).extracting(Customer::getName).contains("Rajan","Rani");
		log.info("givenMultipleCustomers_whenfindingAll_thenReturnAllCustomeredReturned test cases are passed");
	}
	
	@Test
	@DisplayName("givenExistingCustomer_whenUpdated_thenReturnCustomerDetailsAreUpdated")
	public void givenExistingCustomer_whenUpdated_thenReturnCustomerDetailsAreUpdated()
	{
		Customer save = customerRepository.save(customer);
		save.setName("Raja kumar");
		Customer save2 = customerRepository.save(customer);
		assertThat(save2.getName()).isEqualTo("Raja kumar");
		log.info(" update customer test cases ");
	}
	
	@Test
	@DisplayName("givenExistingCustomer_whenDeleted_thenCustomerIsRemoved")
	public void givenExistingCustomer_whenDeleted_thenCustomerIsRemoved()
	{
		Customer savedCustomer = customerRepository.save(customer);
		customerRepository.delete(savedCustomer);
		
		Optional<Customer> findCustomer = customerRepository.findById(savedCustomer.getId());
		assertThat(findCustomer).isEmpty();
		log.info("delete customer test cases");
	}
	
	@Test
	@DisplayName("givenCustomerWithMissingFields_whenSaved_thenCustomerPersistWithNullValue")
	public void givenCustomerWithMissingFields_whenSaved_thenCustomerPersistWithNullValue()
	{
		Customer customer1=new Customer();
		Customer saveCustomer = customerRepository.save(customer1);
		assertThat(saveCustomer.getId()).isNotNull();
		assertThat(saveCustomer.getAddress()).isNull();
		assertThat(saveCustomer.getEmail()).isNull();
		assertThat(saveCustomer.getName()).isNull();
		log.info("missing filed test cases");
	}
	
	@DisplayName("givenDuplicateCustomer_whenShaved_thenReturnBothBothCustomerReturned")
	@Test
	void givenDuplicateCustomer_whenShaved_thenReturnBothBothCustomerReturned()
	{
		Customer saveCustomer = customerRepository.save(customer);
		
		Customer customer1=new Customer();
		customer1.setAddress("Patna");
		customer1.setEmail("rajan@gmail.com");
		customer1.setName("Rajan kumar");
		
		Customer saveCustomer2 = customerRepository.save(customer1);
		assertThat(saveCustomer2).isNotNull();
		assertThat(saveCustomer.getEmail()).isEqualTo("rajan@gmail.com");
		log.info("Duplicate customer save test cases");
				
	}
	
	@Test
	@DisplayName("givenNonExistentCustomerId_whenFindingCustomer_thenEmptyResultReturned")
	void givenNonExistentCustomerId_whenFindingCustomer_thenEmptyResultReturned()
	{
		long nonExistenId=99l;
		Optional<Customer> findByCustomerId = customerRepository.findById(nonExistenId);
		assertThat(findByCustomerId).isEmpty();
		log.info("finding non existing customerId test cases");
		
	}
	
	@Test
	@DisplayName("No Existent Customer when Return No Error Occurs test cases")
	void givenNoExistentCustomer_whenDeleted_thenReturnNoErrorOccurs()
	{
		long nonExistent=99l;
//		Customer saveCustomer1 = customerRepository.save(customer);
		customerRepository.deleteById(nonExistent);
//		customerRepository.deleteAll();
		long count = customerRepository.count();
		assertThat(count).isZero();
		log.info("No Existent Customer when Return No Error Occurs test cases");
	}
	
	@Test
	@Disabled
	@DisplayName("givenNullCustomer_whenSaved_thenReturnIllagalArgumentException")
	void givenNullCustomer_whenSaved_thenReturnIllagalArgumentException()
	{
		assertThrows(IllegalArgumentException.class, () -> {
            customerRepository.save(null);
        });
		log.info("null value test cases");
	}
	
}
