/**
 * 
 */
package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResoucreNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

/**
 * @author deby7
 *10:01:36 pm
 *2024
 *demo
 *TODO
 */
@RestController
//@Controller
@RequestMapping("/api/employees")
public class EmployeeController {
	private EmployeeService employeeService;

	/**
	 * @param employeeService
	 */
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	//build create employee rest api
	@PostMapping()
	public ResponseEntity<Employee>saveEmployee(@RequestBody Employee employee)
	{
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}
	
	// get all employee
	@GetMapping()
	public List<Employee>getAllEmployees()
	{
		return employeeService.getAllEmployees();
	}
	
	// get employee mapping by id 
	@GetMapping("{id}")
	public ResponseEntity<Employee>getEmployeeById(@PathVariable("id") long employeeId)
	{
		return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
	}
	
	// build  update employee rest api
	@PutMapping("{id}")
	public ResponseEntity<Employee>updateEmployee(@PathVariable("id") long Employeeid
			,@RequestBody Employee employee)
	{
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, Employeeid), HttpStatus.OK);
	}
	
	// build delete rest api
	@DeleteMapping("{id}")
	public ResponseEntity<String>deleteEmployee(@PathVariable("id")long Employeeid)
	{
		// delete employee from db
//	    try {
//	        employeeService.deleteEmplyee(id);
//	        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
//	    } catch (ResoucreNotFoundException e) {
//	        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
//	    } catch (Exception e) {
//	    	return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
		employeeService.deleteEmplyee(Employeeid);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
	       
	}
}
