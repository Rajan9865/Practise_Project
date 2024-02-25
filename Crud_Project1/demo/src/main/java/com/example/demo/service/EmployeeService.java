/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

/**
 * @author deby7
 *8:06:43 am
 *2024
 *demo
 *TODO
 */
public interface EmployeeService {
	
	Employee saveEmployee(Employee employee);
	List<Employee>getAllEmployees();
	Employee getEmployeeById(long id);
	Employee updateEmployee(Employee employee,long id);
	void deleteEmplyee(long id);
//	List<Employee>getAllEmployees(Employee employee);

}
