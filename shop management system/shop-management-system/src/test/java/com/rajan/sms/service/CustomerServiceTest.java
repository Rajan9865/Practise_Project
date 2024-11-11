/**
 * 
 */
package com.rajan.sms.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.junit.jupiter.MockitoExtension;

import com.rajan.sms.dto.CustomerDTO;
import com.rajan.sms.entity.Customer;
import com.rajan.sms.exception.ResourceNotFoundException;
import com.rajan.sms.repository.CustomerRepository;
import com.rajan.sms.service.impl.CustomerServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * com.rajan.sms.service
 * 
 * @author Rajan kumar
 */

@Slf4j
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
	
	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerServiceImpl customerService;
	
	private CustomerDTO customerDTO;
	
	private Customer customer;
	
	@BeforeEach
	void setup() {
		customerDTO = new CustomerDTO("Rajan", "rajan@gmail.com", "Patna");
		customer = new Customer();
		customer.setName(customerDTO.getName());
		customer.setEmail(customerDTO.getEmail());
		customer.setAddress(customerDTO.getAddress());
	}

	@AfterEach
	void teatDown() {
		customerRepository.deleteAll();
		log.info("Tear down methods executed and repository cleared after each test.");
	}

	@Test
	@DisplayName("givenCustomerObject_whenValidCustomer_thenAddCustomerObject")
	void givenCustomerObject_whenValidCustomer_thenAddCustomerObject() {
		// given
		given(customerRepository.save(any(Customer.class))).willReturn(customer);

		// when
		log.info("verifying the customerRepository ");
		Customer addCustomer = customerService.addCustomer(customerDTO);

		// then
		log.info("Asserting that the customer's details match the given customerDto");
		then(customerRepository).should().save(any(Customer.class));
		assertThat(addCustomer.getEmail()).isEqualTo(customerDTO.getEmail());
		assertThat(addCustomer.getAddress()).isEqualTo(customerDTO.getAddress());
		assertThat(addCustomer.getName()).isEqualTo(customerDTO.getName());
		log.info("test completes successfully");
	}

	@Test
	@DisplayName("givenCustomer_whenUpdateCustomer_thenCustomerIsUpdated")
	void givenCustomer_whenUpdateCustomer_thenCustomerIsUpdated() {
		// given
		long id = 1l;
		Customer existingCustomer = new Customer();
		existingCustomer.setId(id);
		existingCustomer.setAddress("Raxaul");
		existingCustomer.setEmail("rani@gmail.com");
		existingCustomer.setName("Rani");

		// when
		given(customerRepository.findById(id)).willReturn(Optional.of(existingCustomer));
		given(customerRepository.save(existingCustomer)).willReturn(existingCustomer);

		// then
		Customer updateCustomer = customerService.updateCustomer(id, customerDTO);

		// then
		then(customerRepository).should().findById(id);
		then(customerRepository).should().save(existingCustomer);

		// assertations
		assertThat(updateCustomer.getName()).isEqualTo(customerDTO.getName());
		assertThat(updateCustomer.getEmail()).isEqualTo(customerDTO.getEmail());
		assertThat(updateCustomer.getAddress()).isEqualTo(customerDTO.getAddress());
	}

	@Test
	@DisplayName("givenCusstomer_whenUpdatingNonExistingCustomer_thenThrowException")
	void givenCusstomer_whenUpdatingNonExistingCustomer_thenThrowException() {
		// given
		long id = 1l;
		CustomerDTO customerDTO = new CustomerDTO("Updated Name", "updated.email@example.com", "Updated Address");
		given(customerRepository.findById(id)).willReturn(Optional.empty());

		// when/then
		assertThrows(ResourceNotFoundException.class, () -> customerService.updateCustomer(id, customerDTO));
		then(customerRepository).should().findById(id);
		then(customerRepository).shouldHaveNoMoreInteractions();
	}
	
	@Test
	@Disabled
	@DisplayName("givenCustomer_whenDeleteExistingCustomer_thenCustomerIsDeleted")
	void givenCustomer_whenDeleteExistingCustomer_thenCustomerIsDeleted()
	{
		Customer saveCustomer = customerRepository.save(customer);
		//given
		willDoNothing().given(customerRepository).deleteById(saveCustomer.getId());
		//when
		customerService.deleteCustomer(saveCustomer.getId());
		//then
		then(customerRepository).should().deleteById(saveCustomer.getId());
	}
	
	@Test
	@DisplayName("givenCustomer_whenDeleteNonExistingCustomer_thenThrowException")
	void givenCustomer_whenDeleteNonExistingCustomer_thenThrowException()
	{
		long id=1l;
		willThrow(new ResourceNotFoundException("customer not found", 1l)).given(customerRepository).deleteById(id);;
		
		//when/then
		assertThrows(ResourceNotFoundException.class, ()->customerService.deleteCustomer(id));
		then(customerRepository).should().deleteById(id);
	}
	@Test
	@DisplayName("givenCustomer_whenExistsCustomers_thenReturnAllCustomers")
	void givenCustomer_whenExistsCustomers_thenReturnAllCustomers()
	{
		Customer customer1=new Customer();
		customer1.setName("Radha");
		List<Customer> customers = Arrays.asList(customer,customer1);
		given(customerRepository.findAll()).willReturn(customers);
		
		//when
		List<Customer> allCustomers = customerService.getAllCustomers();
		
		//then
		then(customerRepository).should().findAll();
		assertThat(allCustomers).hasSize(2);
		assertThat(allCustomers).containsExactlyInAnyOrder(customer1,customer);
	}
	
	@Test
	@DisplayName("givenCustomer_whenNonCustomerExist_thenreturnEmptyList")
	void givenCustomer_whenNonCustomerExist_thenreturnEmptyList()
	{
		given(customerRepository.findAll()).willReturn(List.of());
		
		//when
		List<Customer> allCustomers = customerService.getAllCustomers();
		
		//then
		then(customerRepository).should().findAll();
		assertThat(allCustomers).isEmpty();
	}
	
	@Test
	@DisplayName("givenCustomer_whenCustomerNotFoundById_thenThrowException")
	void givenCustomer_whenCustomerNotFoundById_thenThrowException() {
		long id = 1l;
		given(customerRepository.findById(id)).willReturn(Optional.empty());

		// when/then
		assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomerById(id));
		then(customerRepository).should().findById(id);
	}
	
	@Test
	@DisplayName("givenCustomer_whenCustomerExistById_thenReturnCustomer")
	void givenCustomer_whenCustomerExistById_thenReturnCustomer()
	{
		//given
		given(customerRepository.findById(customer.getId())).willReturn(Optional.of(customer));
		
		//when
		Customer foundCustomer = customerService.getCustomerById(customer.getId());
		log.info("customer id by repository :{}",foundCustomer.getId());
		log.info("customer id by entity :{}",customer.getId());
		
		//then
		then(customerRepository).should().findById(customer.getId());
		assertThat(foundCustomer.getId()).isEqualTo(customer.getId());
		assertThat(foundCustomer.getAddress()).isEqualTo(customer.getAddress());
		assertThat(foundCustomer.getEmail()).isEqualTo(customer.getEmail());
		assertThat(foundCustomer.getName()).isEqualTo(customer.getName());
		
	}
}
