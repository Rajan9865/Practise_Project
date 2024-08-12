/**
 * 
 */
package com.crud.example.service;

import java.util.List;

import com.crud.example.dto.EmployeeDto;

/**
 * com.crud.example.service DelL
 * @author Rajan kumar
 */
public interface EmployeeService {
	// create employee
	EmployeeDto createEmployee(EmployeeDto employeeDto);
	
	// to get single employee
	EmployeeDto getEmployeeI(Long employeeId);

	// to get all employees
	List<EmployeeDto>getAllEmployees();
	
	//to update employee
	EmployeeDto updateEmployee(Long employeeId,EmployeeDto updateEmployee);
	
	// to delete employe by id
	void deleteEmployee(Long employeeId);
	
	
}
