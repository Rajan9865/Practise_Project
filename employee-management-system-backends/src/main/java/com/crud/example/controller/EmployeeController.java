/**
 * 
 */
package com.crud.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.example.dto.EmployeeDto;
import com.crud.example.service.EmployeeService;

/**
 * com.crud.example.controller DelL
 * @author Rajan kumar
 */
//@CrossOrigin("*")
@RestController
@RequestMapping("api/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	//to create a employee rest api
	@PostMapping
	public ResponseEntity<EmployeeDto>createEmploye(@RequestBody EmployeeDto employeeDto)
	{
		EmployeeDto saveEmployee=employeeService.createEmployee(employeeDto);
		return new ResponseEntity<EmployeeDto>(saveEmployee,HttpStatus.CREATED);
	}
	
	// to get single employee rest api
	@GetMapping("{id}")
	public ResponseEntity<EmployeeDto>getEmployee(@PathVariable("id") Long employeeId)
	{
		EmployeeDto employeeDto=employeeService.getEmployeeI(employeeId);
		return ResponseEntity.ok(employeeDto);
	}
	
	// to get all employees rest api
	@GetMapping
	public ResponseEntity<List<EmployeeDto>>getAllEmployee()
	{
		List<EmployeeDto>employees=employeeService.getAllEmployees();
		return ResponseEntity.ok(employees);
	}
	
	// to update a single employee rest api
	@PutMapping("{id}")
	public ResponseEntity<EmployeeDto>updateEmployee(@PathVariable("id")Long employeeId,@RequestBody EmployeeDto employeeDto)
	{
		EmployeeDto updateemployees=employeeService.updateEmployee(employeeId, employeeDto);
		return ResponseEntity.ok(updateemployees);
	}
	
	// to delete single employee Rest api
	@DeleteMapping("{id}")
	public ResponseEntity<String>deleteEmployee(@PathVariable("id") Long employeeId)
	{
		employeeService.deleteEmployee(employeeId);
		return ResponseEntity.ok("Employee deteted succuessfully !");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
