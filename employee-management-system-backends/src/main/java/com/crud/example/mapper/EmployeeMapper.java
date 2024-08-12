/**
 * 
 */
package com.crud.example.mapper;

import com.crud.example.dto.EmployeeDto;
import com.crud.example.entity.Employee;

/**
 * com.crud.example.mapper DelL
 * 
 * @author Rajan kumar
 */
public class EmployeeMapper {
//	convert  Employee jpa entity into EmployeeDto
	public static EmployeeDto mapToEmployeeDto(Employee employee) {
		return new EmployeeDto(employee.getId(), 
				employee.getFirstName(), 
				employee.getLastName(), 
				employee.getEmail());
	}
//	convert  EmployeeDto   into Employee Jpa entity
	public static Employee mapToEmployee(EmployeeDto employeeDto)
	{
		return new Employee(
				employeeDto.getId(),
				employeeDto.getFirstName(),
				employeeDto.getLastName(),
				employeeDto.getEmail()
				);
	}
}
