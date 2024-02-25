/**
 * 
 */
package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResoucreNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

/**
 * @author deby7
 *8:07:28 am
 *2024
 *demo
 *TODO
 */
@Service
public class EmployeeServiceImpl  implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	/**
	 * @param employeeRepository
	 */
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
 }

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		return employeeRepository.findById(id).orElseThrow(()->new ResoucreNotFoundException("Employee", "Id", id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// we need to check whether employee given id is exist in db or not
		Employee existEmployee=employeeRepository.findById(id).orElseThrow(
				()-> new ResoucreNotFoundException("Employee", "Id", id));
		existEmployee.setName(employee.getName());
		existEmployee.setLastname(employee.getLastname());
		existEmployee.setEmail(employee.getEmail());
		// save existing employee to db
		employeeRepository.save(existEmployee);
		return existEmployee;
	}

	@Override
	public void deleteEmplyee(long id) {
		// check wheather a emloyee exist in db or not
		 Employee employee = employeeRepository.findById(id)
		            .orElseThrow(() -> new ResoucreNotFoundException("Employee", "Id", id));

		    // if the employee exists, delete it
		    employeeRepository.deleteById(id);
		 
	}



}
