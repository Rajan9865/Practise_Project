/**
 * 
 */
package com.crud.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.example.dto.EmployeeDto;
import com.crud.example.entity.Employee;
import com.crud.example.exception.ResourceNotFoundException;
import com.crud.example.mapper.EmployeeMapper;
import com.crud.example.repository.EmployeeRepository;
import com.crud.example.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

/**
 * com.crud.example.service.impl DelL
 * @author Rajan kumar
 */
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		Employee employee=EmployeeMapper.mapToEmployee(employeeDto);
		Employee saveEmployee = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(saveEmployee);
	}

	@Override
	public EmployeeDto getEmployeeI(Long employeeId) {
			Employee employee=employeeRepository.findById(employeeId)
					.orElseThrow(()-> new ResourceNotFoundException(" Employee is not exists with given id :"+employeeId));
			return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee>employees=employeeRepository.findAll();
		return employees.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee))
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
		Employee employee=employeeRepository.findById(employeeId)
				.orElseThrow(()-> new ResourceNotFoundException("Employee is not exists with given id :"+employeeId));
		employee.setFirstName(updateEmployee.getFirstName());
		employee.setLastName(updateEmployee.getLastName());
		employee.setEmail(updateEmployee.getEmail());
		
		Employee updateEmployeeObj=employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(updateEmployeeObj);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		employeeRepository.findById(employeeId).orElseThrow(()->
		new ResourceNotFoundException("EMployee is not exist with given id "+employeeId));
		employeeRepository.deleteById(employeeId);
	}

}
